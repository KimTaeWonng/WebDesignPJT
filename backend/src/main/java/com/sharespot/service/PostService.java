package com.sharespot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sharespot.entity.Post;
import com.sharespot.repo.GroupRepository;
import com.sharespot.repo.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> CurationList(String big, String[] small, String[] who, String[] where) {

		List<Post> all = postRepository.findAll();
		List<Post> curatioN1 = new ArrayList<>();
		List<Post> curatioN2 = new ArrayList<>();
		List<Post> curatioN3 = new ArrayList<>();
		List<Post> curatioN4 = new ArrayList<>();

		if (big.length() != 0) {
			for (Post p : all) {
				if (p.getClassBig().contains(big)) {
					curatioN1.add(p);
				}
			}
		} else {
			curatioN1 = postRepository.findAll();
		}

		if (small.length != 0) {
			for (Post p : curatioN1) {
				for (String s : small) {
					if (p.getClassSmall().contains(s)) {
						curatioN2.add(p);
						break;
					}
				}
			}
		} else if (big.length() == 0) {
			curatioN2 = postRepository.findAll();
		}

		if (who.length !=0) {
			for (Post p : curatioN2) {
				for (String s : who) {
					if (p.getClassWho().equals(s)) {
						curatioN3.add(p);
						break;
					}
				}
			}
		} else if (big.length() == 0) {
			curatioN3 = postRepository.findAll();
		}

		if (where.length!=0) {
			for (Post p : curatioN3) {
				for (String s : where) {
					if (p.getClassWhere().equals(s)) {
						curatioN4.add(p);
						break;
					}
				}
			}
		}

		if (where.length == 0) {
			return curatioN3;
		} else {
			return curatioN4;
		}
	}
	
	public List<Post> defaultList(){
		return postRepository.findAllByOrderByUploadTimeDesc();
	}

}
