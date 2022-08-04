package com.sharespot.controller;

import com.sharespot.entity.Group;
import com.sharespot.service.GroupService;
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
    public ResponseEntity<List<Group>> getAllGroups(){
        List<Group> groups = groupService.findAllGroup();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("{gid}")
    public ResponseEntity<Optional<Group>> getGroup(@PathVariable int gid){
        Optional<Group> group = groupService.findByGroupId(gid);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping
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
                .build();
        groupService.createGroup(groupEntity);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @DeleteMapping("{gid}")
    public ResponseEntity<Integer> deleteGroup(@PathVariable int gid){
        return new ResponseEntity<Integer>(groupService.deleteGroup(gid), HttpStatus.OK);
    }

    @PutMapping("{gid}")
    public ResponseEntity<Integer> updateGroup(@RequestBody Group group){
        Group groupEntity = Group.builder()
                .group_manager(group.getGroup_manager())
                .group_name(group.getGroup_name())
                .group_content(group.getGroup_content())
                .group_limit(group.getGroup_limit())
                .group_gender(group.getGroup_gender())
                .group_age_min(group.getGroup_age_min())
                .group_age_max(group.getGroup_age_max())
                .group_image(group.getGroup_image())
                .build();

        int result = groupService.updateGroup(group.getGroup_id(), groupEntity);

        return new ResponseEntity<Integer>(result, HttpStatus.OK);

    }

}
