package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.Parent;
import org.springframework.stereotype.Service;
import org.example.repository.ParentRepository;
import org.example.repository.StudentRepository;
import org.example.response.ParentResponse;
import org.example.service.ParentService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository repository;
    private final StudentRepository studentRepository;

    @Override
    public List<ParentResponse> getParents() {
        List<ParentResponse> response = new ArrayList<>();
        for (Parent parent : repository.findAll()) {
            response.add(new ParentResponse(
                    parent.getName(),
                    parent.getSurname()));
        }
        return response;
    }

    @Override
    public ParentResponse getParentByStudent(Long id) {
        Parent parent = repository.getParentByStudent(studentRepository.getById(id));
        return toResponse.apply(parent);
    }

    @Override
    public ParentResponse getParent(Long id) {
        Parent parent = repository.findById(id).orElse(null);
        if(parent!=null){
            return toResponse.apply(parent);
        }
        return null;
    }

    @Override
    public void addParent(ParentResponse parent) {
        repository.save(Parent.builder().name(parent.getName()).surname(parent.getSurname()).build());
    }

    @Override
    public void deleteParent(Long id) {
        repository.delete(repository.getById(id));
    }

    @Override
    public void updateParent(ParentResponse parent, Long id) {
        Parent oldParent = repository.findById(id).orElse(null);
        if(oldParent!=null){
            oldParent.setName(parent.getName());
            oldParent.setSurname(parent.getSurname());
            repository.save(oldParent);
        }
    }

    private final Function<Parent, ParentResponse> toResponse = ent -> {
        return new ParentResponse(ent.getName(), ent.getSurname());
    };
}
