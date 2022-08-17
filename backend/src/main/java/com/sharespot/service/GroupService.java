package com.sharespot.service;

import com.sharespot.entity.Group;
import com.sharespot.repo.GroupRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    public List<Group> findAllGroup(){
        return groupRepository.findAll();
    }
    public Optional<Group> findByGroupId(Integer gid){
        return groupRepository.findById(gid);
    }

    public Group createGroup(Group group){
        Group savedGroup = groupRepository.save(group);
        return savedGroup;
    }

    public int deleteGroup(Integer gid){
        groupRepository.deleteById(gid);
        return 1;
    }

    public int updateGroup(Integer gid, Group value){
        Optional<Group> mGroup = groupRepository.findById(gid);
        if(mGroup.isPresent()){
            Group group = mGroup.get();

            group.setGroupManager(value.getGroupManager());
            group.setGroupName(value.getGroupName());
            group.setGroupContent(value.getGroupContent());
            group.setGroupLimit(value.getGroupLimit());
            group.setGroupGender(value.getGroupGender());
            group.setGroupAgeMin(value.getGroupAgeMin());
            group.setGroupAgeMax(value.getGroupAgeMax());
            group.setGroupImage(value.getGroupImage());
            groupRepository.save(group);

            return 1;
        }
        return 0;
    }


}
