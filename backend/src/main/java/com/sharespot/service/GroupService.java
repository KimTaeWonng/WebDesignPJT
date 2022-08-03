package com.sharespot.service;

import com.sharespot.entity.Group;
import com.sharespot.repo.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findAllGroup(){
        return groupRepository.findAll();
    }
    public Optional<Group> findByGroup_id(Integer gid){
        return groupRepository.findById(gid);
    }

    public Group createGroup(Group group){
        Group savedGroup = groupRepository.save(group);
        return savedGroup;
    }




}
