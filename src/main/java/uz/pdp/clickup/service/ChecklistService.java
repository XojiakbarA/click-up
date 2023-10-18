package uz.pdp.clickup.service;

import uz.pdp.clickup.dto.request.ChecklistRequest;
import uz.pdp.clickup.dto.view.ChecklistView;
import uz.pdp.clickup.entity.Checklist;

import java.util.List;

public interface ChecklistService {
    List<ChecklistView> getAllByTaskId(Long taskId);

    ChecklistView create(ChecklistRequest request);

    ChecklistView updateById(ChecklistRequest request, Long id);

    void deleteById(Long id);

    Checklist findById(Long id);
}
