
export const loginAction = (userEmail) => {
     return {
        type:"LOGIN_SUCCESS_ACTION",
        payload : userEmail
    }
}

export const updateLoginStatusAction = (status) => {
    return{
        type:"LOGIN_STATUS_UPDATE_ACTION",
        payload : status
    }
}
