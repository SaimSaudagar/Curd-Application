export class Constants{
    public static SERVER_URL: string = "http://localhost:8080/";
    public static USERS_RESOURCE: string = Constants.SERVER_URL + "api/users/";

    public static FIND_ALL_USERS: string = Constants.USERS_RESOURCE
    public static FIND_BY_ID: string = Constants.USERS_RESOURCE //+ id
    public static ADD_NEW_USER: string = this.USERS_RESOURCE + 'add'
    public static FIND_BY_CRITERIA: string = Constants.USERS_RESOURCE + "search?criteria=" // + criteria + searchItem
    public static DELETE_USER: string = Constants.USERS_RESOURCE + "delete/" // + id
    public static UPDATE_USER: string = Constants.USERS_RESOURCE + "update"
    public static PASSWORD_CHECKER: string = "http://localhost:8080/api/users/check"

    public static MANDATORY_FIELDS_ERROR_MESSAGE: string = "Fields cannot be empty"
    public static USER_NOT_FOUND: string = "User not found"
    public static NOT_A_NUMBER_ERROR_MESSAGE: string = "Not a number"
    public static INTERNAL_SERVER_ERROR_MESSAGE: string = "Error in comunicatin with server"
    public static USER_NOT_FOUND_FOR_CRITERIA_ERROR_MEASSAGE: string = "No data found based on criteria"
    public static UPDATED_USER_INFO_MESSAGE: string = "User Updated"
    
    public static CHECK_LOGIN: Boolean = false
}