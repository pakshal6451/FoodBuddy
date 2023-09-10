package foodBuddy.foodBuddy.groupManagement;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/groupApi")
@AllArgsConstructor
@CrossOrigin
public class GroupController {
    private AppGroupService appGroupService;
    @PostMapping("/Create")
    public GroupCreationResponse createGroup(@RequestBody GroupCreationRequest request){
        return appGroupService.createGroup(request);
    }
    @PostMapping("/Join")
    public GroupJoinResponse joinGroup(@RequestBody GroupJoinRequest request){
        GroupJoinResponse response = appGroupService.joinGroup(request);
        return response;
    }
    @GetMapping("/view")
    public ViewGroupUsersResponse viewGroup(@RequestParam(value = "groupCode") String groupCode){
        ViewGroupUsersResponse response = appGroupService.findGroupUsers(groupCode);
        return response;
    }
    @PostMapping("/Leave")
    public LeaveGroupResponse leaveGroup(@RequestBody LeaveGroupRequest request){
        LeaveGroupResponse response = appGroupService.leaveGroup(request);
        return response;
    }
}
