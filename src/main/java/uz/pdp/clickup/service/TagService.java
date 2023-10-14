package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.request.TagRequest;
import uz.pdp.clickup.dto.view.TagView;

public interface TagService {

    List<TagView> getAllByWorkspaceId(Long id);

    TagView getById(Long id);

    TagView create(TagRequest request);

    TagView updateById(TagRequest request, Long id);

    void deleteById(Long id);
    
}
