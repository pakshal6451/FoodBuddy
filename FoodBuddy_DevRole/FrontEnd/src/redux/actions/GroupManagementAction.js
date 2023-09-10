export const updateGroupName = (groupName) => {
    return{
        type:"GROUP_CREATION_ACTION",
        payload : groupName
    }
}

export const updateGroupNumber = (groupCode) => {
    return{
        type:"UPDATE_GROUP_CODE",
        payload : groupCode
    }
}