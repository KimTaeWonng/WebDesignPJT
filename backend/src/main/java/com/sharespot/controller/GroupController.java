package com.sharespot.controller;

import com.sharespot.entity.Group;
import com.sharespot.service.GroupService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
@AllArgsConstructor
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    @ApiOperation(value = "그룹목록", notes = "<b>그룹 전체 목록</b>을 반환한다.")
    public ResponseEntity<List<Group>> getAllGroups(){
        List<Group> groups = groupService.findAllGroup();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("{gid}")
    @ApiOperation(value = "그룹", notes = "해당 그룹id의 <b>그룹</b>을 반환한다.")
    public ResponseEntity<Optional<Group>> getGroup(@PathVariable int gid){
        Optional<Group> group = groupService.findByGroupId(gid);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "그룹작성", notes = "<b>그룹</b>을 작성한다.")
    public ResponseEntity<Integer> createGroup(@RequestBody Group group){
        Group groupEntity = Group.builder()
                .group_manager(group.getGroup_manager())
                .group_name(group.getGroup_name())
                .group_content(group.getGroup_content())
                .group_limit(group.getGroup_limit())
                .group_gender(group.getGroup_gender())
                .group_age_min(group.getGroup_age_min())
                .group_age_max(group.getGroup_age_max())
                .group_image(group.getGroup_image())
                .group_nick(group.getGroup_nick())
                .build();
        groupService.createGroup(groupEntity);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @DeleteMapping("{gid}")
    @ApiOperation(value = "그룹삭제", notes = "해당 <b>그룹</b>을 삭제한다.")
    public ResponseEntity<Integer> deleteGroup(@PathVariable int gid){
        return new ResponseEntity<Integer>(groupService.deleteGroup(gid), HttpStatus.OK);
    }

    @PutMapping("{gid}")
    @ApiOperation(value = "그룹수정", notes = "해당 <b>그룹</b>을 수정한다.")
    public ResponseEntity<Integer> updateGroup(@RequestBody Group group, @PathVariable int gid){
        Group groupEntity = Group.builder()
                .group_manager(group.getGroup_manager())
                .group_name(group.getGroup_name())
                .group_content(group.getGroup_content())
                .group_limit(group.getGroup_limit())
                .group_gender(group.getGroup_gender())
                .group_age_min(group.getGroup_age_min())
                .group_age_max(group.getGroup_age_max())
                .group_image(group.getGroup_image())
                .group_nick(group.getGroup_nick())
                .build();

        int result = groupService.updateGroup(gid, groupEntity);

        return new ResponseEntity<Integer>(result, HttpStatus.OK);

    }

}
