package uz.pdp.clickup.service;

import java.util.List;

import uz.pdp.clickup.dto.request.ListRequest;
import uz.pdp.clickup.dto.view.ListView;

public interface ListService {

    List<ListView> getAllByFolderId(Long id);

    ListView create(ListRequest request);

    ListView updateById(ListRequest request, Long id);

    void deleteById(Long id);

    uz.pdp.clickup.entity.List findById(Long id);
}
