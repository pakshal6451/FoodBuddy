import { combineReducers } from "redux";
import { LoginLogoutReducer } from "./LoginLogoutReducer";
import { GroupManagementReducer } from "./GroupManagementReducer";

const reducers = combineReducers({
    loginLogoutReducer : LoginLogoutReducer,
    groupManagementReducer : GroupManagementReducer
})

export default reducers