
const defaultstate = {
        emailId:"",
        loginStatus:false
}

export const LoginLogoutReducer = (state = defaultstate ,action) => {

    switch(action.type){
        case 'LOGIN_SUCCESS_ACTION' :
            return {
                emailId : action.payload
            }

        case 'LOGIN_STATUS_UPDATE_ACTION' :
            return {
                loginStatus:action.payload
            }


        default : return state
     }

}