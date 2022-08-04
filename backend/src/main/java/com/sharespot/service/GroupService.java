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

            group.setGroup_manager(value.getGroup_manager());
            group.setGroup_name(value.getGroup_name());
            group.setGroup_content(value.getGroup_content());
            group.setGroup_limit(value.getGroup_limit());
            group.setGroup_gender(value.getGroup_gender());
            group.setGroup_age_min(value.getGroup_age_min());
            group.setGroup_age_max(value.getGroup_age_max());
            group.setGroup_image(value.getGroup_image());
            groupRepository.save(group);

            return 1;
        }
        return 0;
    }


}
