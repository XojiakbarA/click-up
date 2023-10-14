package uz.pdp.clickup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import uz.pdp.clickup.dto.request.CommentRequest;
import uz.pdp.clickup.dto.view.CommentView;
import uz.pdp.clickup.entity.Comment;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.CommentMapper;
import uz.pdp.clickup.repository.CommentRepository;
import uz.pdp.clickup.service.CommentService;
import uz.pdp.clickup.service.TaskService;

@Service
public class CommentServiceImpl implements CommentService {

    private final String resourceName = Comment.class.getSimpleName();

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CommentMapper commentMapper;

    private Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    private Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentView> getAllByTaskId(Long id) {
        return commentRepository.findAllByTaskId(id).stream().map(commentMapper::mapToView).toList();
    }

    @Override
    public void deleteById(Long id) {
        if (commentRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        commentRepository.deleteById(id);
    }

    @Override
    public CommentView getById(Long id) {
        return commentMapper.mapToView(findById(id));
    }

    @Override
    public CommentView create(@Valid CommentRequest request) {
        Comment comment = new Comment();

        if (request.getTaskId() != null) {
            comment.setTask(taskService.findById(request.getTaskId()));
        }
        if (request.getContent() != null && !request.getContent().isBlank()) {
            comment.setContent(request.getContent());
        }

        return commentMapper.mapToView(save(comment));
    }

    @Override
    public CommentView updateById(@Valid CommentRequest request, Long id) {
        Comment comment = findById(id);

        if (request.getTaskId() != null) {
            comment.setTask(taskService.findById(request.getTaskId()));
        }
        if (request.getContent() != null && !request.getContent().isBlank()) {
            comment.setContent(request.getContent());
        }

        return commentMapper.mapToView(save(comment));
    }
    
}
