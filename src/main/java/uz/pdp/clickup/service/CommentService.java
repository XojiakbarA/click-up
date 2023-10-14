package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.request.CommentRequest;
import uz.pdp.clickup.dto.view.CommentView;

public interface CommentService {

    List<CommentView> getAllByTaskId(Long id);

    void deleteById(Long id);

    CommentView getById(Long id);

    CommentView create(CommentRequest request);

    CommentView updateById(CommentRequest request, Long id);
    
}
