package org.example.service;

import org.example.response.ParentResponse;

import java.util.List;

public interface ParentService {

    List<ParentResponse> getParents();

    ParentResponse getParentByStudent(Long id);

    ParentResponse getParent(Long id);

    void addParent(ParentResponse parent);

    void deleteParent(Long id);

    void updateParent(ParentResponse parent, Long id);
}
