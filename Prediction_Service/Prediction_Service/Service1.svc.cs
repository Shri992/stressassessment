using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Net.Mail;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.ServiceModel.Web;
using System.Text;

namespace Prediction_Service
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
     [AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Required)]
    public class Service1 : IService1
    {
        string constr = ConfigurationManager.ConnectionStrings["connect"].ConnectionString;
        SqlConnection con;
        SqlCommand cmd;
        SqlDataAdapter da;
        DataTable dt;

        public ResponseMessage registerUser(User user)
        {
            ResponseMessage resp = new ResponseMessage();

            try
            {
                con = new SqlConnection(constr);
                cmd = new SqlCommand("usp_save_mstUser", con);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@iUserID", Convert.ToInt32(user.UserID));
                cmd.Parameters.AddWithValue("@iName", user.Name);
                cmd.Parameters.AddWithValue("@iEmailID", user.EmailID);
                cmd.Parameters.AddWithValue("@iPassword", user.Password);
                cmd.Parameters.AddWithValue("@iMobileNo", user.MobileNo);
                da = new SqlDataAdapter(cmd);
                dt = new DataTable();
                da.Fill(dt);
                if (dt.Rows.Count > 0)
                {
                    resp.Status = Convert.ToInt32(dt.Rows[0]["Status"]);
                    resp.Message = dt.Rows[0]["Message"].ToString();
                }
                else
                {
                    resp.Status = 409;
                    resp.Message = "Something went wrong";
                }

            }
            catch (Exception ex)
            {
                resp.Status = 409;
                resp.Message = ex.ToString();
            }
            return resp;
        }

        public ResponseLogin login(string EmailID, string Password)
        {
            ResponseLogin resp = new ResponseLogin();
            try
            {
                con = new SqlConnection(constr);
                cmd = new SqlCommand("usp_login", con);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@iEmailID", EmailID);
                cmd.Parameters.AddWithValue("@iPassword", Password);
                da = new SqlDataAdapter(cmd);
                dt = new DataTable();
                da.Fill(dt);
                if (dt.Rows.Count > 0)
                {
                    resp.Status = Convert.ToInt32(dt.Rows[0]["Status"]);
                    resp.LoginUserID = Convert.ToInt32(dt.Rows[0]["LoginUserID"]);
                    resp.Message = dt.Rows[0]["Message"].ToString();
                }
                else
                {
                    resp.Status = 409;
                    resp.LoginUserID = 0;
                    resp.Message = "Something went wrong";
                }
            }
            catch (Exception ex)
            {
                resp.Status = 409;
                resp.LoginUserID = 0;
                resp.Message = ex.ToString();
            }
            return resp;
        }

        public List<Assessment> getQuestions()
        {
            List<Assessment> list = new List<Assessment>();

            try
            {
                con = new SqlConnection(constr);
                cmd = new SqlCommand("usp_select_mstQuestion", con);
                cmd.CommandType = CommandType.StoredProcedure;
                da = new SqlDataAdapter(cmd);
                dt = new DataTable();
                da.Fill(dt);
                if (dt.Rows.Count > 0)
                {
                    for (int i = 0; i < dt.Rows.Count; i++)
                    {
                        Assessment a = new Assessment
                        {
                            QuestionID = Convert.ToInt32(dt.Rows[i]["QuestionID"]),
                            QuestionTypeID = Convert.ToInt32(dt.Rows[i]["QuestionTypeID"]),
                            Question = dt.Rows[i]["Question"].ToString(),
                            Option1 = dt.Rows[i]["Option1"].ToString(),
                            Option2 = dt.Rows[i]["Option2"].ToString(),
                            Option3 = dt.Rows[i]["Option3"].ToString(),
                            Option4 = dt.Rows[i]["Option4"].ToString()
                        };
                        list.Add(a);
                    }
                }
            }
            catch (Exception ex)
            {
               
            }
            return list;
        }

        public List<User> getUserProfile(string UserID)
        {
            List<User> list = new List<User>();
            try
            {
                con = new SqlConnection(constr);
                cmd = new SqlCommand("usp_select_mstUser", con);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@iUserID", Convert.ToInt32(UserID));
                da = new SqlDataAdapter(cmd);
                dt = new DataTable();
                da.Fill(dt);
                if (dt.Rows.Count > 0)
                {
                    for (int i = 0; i < dt.Rows.Count; i++)
                    {
                        User u = new User
                        {
                            UserID = dt.Rows[i]["UserID"].ToString(),
                            Name = dt.Rows[i]["Name"].ToString(),
                            EmailID = dt.Rows[i]["EmailID"].ToString(),
                            Password = dt.Rows[i]["Password"].ToString(),
                            MobileNo = dt.Rows[i]["MobileNo"].ToString()                         
                        };
                        list.Add(u);
                    }
                }
            }
            catch(Exception ex)
            {

            }
            return list;
        }

        public ResponseMessage forgotPassword(string EmailID)
        {
            try
            {
                con = new SqlConnection(constr);
                cmd = new SqlCommand("select * from mstUser where EmailID = @iEmailID", con);
                cmd.Parameters.AddWithValue("@iEmailID", EmailID);
                da = new SqlDataAdapter(cmd);
                dt = new DataTable();
                da.Fill(dt);
                if(dt.Rows.Count >0)
                {
                    string Password = dt.Rows[0]["Password"].ToString();
                    if (sendMail(EmailID, "Your password is:"+Password)) 
                    {
                        return new ResponseMessage { Message = "Please check your email" };
                    }
                    else
                    {
                        return new ResponseMessage { Message = "Error while sending email" };
                    }
                }
                else
                {
                    return new ResponseMessage { Message = "Something went wrong" };
                }
            }
            catch(Exception ex)
            {
                return new ResponseMessage { Message = ex.ToString() };
            }
        }

        private bool sendMail(string email, string body)     // send email when status updates
        {
            try
            {
                MailMessage mail = new MailMessage();
                SmtpClient SmtpServer = new SmtpClient("demoproject.in");

                mail.From = new MailAddress("test@demoproject.in");
                //recipient address
                mail.To.Add(new MailAddress(email));    // receiver's email address
                mail.Subject = "New Notification";
                mail.Body = body;
                SmtpServer.Port = 25;
                SmtpServer.Credentials = new System.Net.NetworkCredential("test@demoproject.in", "Password@123");
                SmtpServer.Send(mail);
                return true;
            }
            catch (Exception ex)
            {
                return false;
                throw ex;        
            }
        }
    }
}
