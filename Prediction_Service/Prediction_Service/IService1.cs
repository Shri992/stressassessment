﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace Prediction_Service
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IService1
    {

        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "registerUser", ResponseFormat = WebMessageFormat.Json)]
        ResponseMessage registerUser(User user);

        [OperationContract]
        [WebGet(UriTemplate = "login/{EmailID}/{Password}", ResponseFormat = WebMessageFormat.Json)]
        ResponseLogin login(string EmailID, string Password);

        [OperationContract]
        [WebGet(UriTemplate = "getQuestions", ResponseFormat = WebMessageFormat.Json)]
        List<Assessment> getQuestions();

        [OperationContract]
        [WebGet(UriTemplate = "getUserProfile/{UserID}", ResponseFormat = WebMessageFormat.Json)]
        List<User> getUserProfile(string UserID);

        [OperationContract]
        [WebGet(UriTemplate = "forgotPassword/{EmailID}/", ResponseFormat = WebMessageFormat.Json)]
        ResponseMessage forgotPassword(string EmailID);

        // TODO: Add your service operations here
    }


    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    [DataContract]
    public class ResponseMessage
    {
        [DataMember]
        public int Status { get; set; }

        [DataMember]
        public string Message { get; set; }
    }

    public class ResponseLogin
    {
        [DataMember]
        public int Status { get; set; }

        [DataMember]
        public int LoginUserID { get; set; }

        [DataMember]
        public string Message { get; set; }
    }

    public class User
    {
        [DataMember]
        public string UserID { get; set; }

        [DataMember]
        public string Name { get; set; }

        [DataMember]
        public string EmailID { get; set; }

        [DataMember]
        public string Password { get; set; }

        [DataMember]
        public string MobileNo { get; set; }

    }

    public class Assessment
    {
        [DataMember]
        public int QuestionID { get; set; }

        [DataMember]
        public int QuestionTypeID { get; set; }

        [DataMember]
        public string Question { get; set; }

        [DataMember]
        public string Option1 { get; set; }

        [DataMember]
        public string Option2 { get; set; }

        [DataMember]
        public string Option3 { get; set; }

        [DataMember]
        public string Option4 { get; set; }
    }
}
