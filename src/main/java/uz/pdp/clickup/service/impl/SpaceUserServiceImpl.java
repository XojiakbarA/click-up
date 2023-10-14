package uz.pdp.clickup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.dto.view.SpaceUserView;
import uz.pdp.clickup.entity.Space;
import uz.pdp.clickup.entity.SpaceUser;
import uz.pdp.clickup.entity.WorkspaceUser;
import uz.pdp.clickup.exception.ResourceExistsException;
import uz.pdp.clickup.exception.ResourceNotFoundException;
import uz.pdp.clickup.mapper.SpaceUserMapper;
import uz.pdp.clickup.repository.SpaceUserRepository;
import uz.pdp.clickup.service.SpaceService;
import uz.pdp.clickup.service.SpaceUserService;
import uz.pdp.clickup.service.WorkspaceUserService;

import java.util.List;

@Service
public class SpaceUserServiceImpl implements SpaceUserService {

    private final String resourceName = SpaceUser.class.getSimpleName();

    @Autowired
    private SpaceUserRepository spaceUserRepository;
    @Autowired
    private SpaceService spaceService;
    @Autowired
    private WorkspaceUserService workspaceUserService;
    @Autowired
    private SpaceUserMapper spaceUserMapper;

    private SpaceUser save(SpaceUser spaceUser) {
        return spaceUserRepository.save(spaceUser);
    }
    private void checkByExistsBySpaceIdAndPersonId(Long spaceId, Long personId) {
        if (spaceUserRepository.existsBySpaceIdAndPersonId(spaceId, personId)) {
            throw new ResourceExistsException(resourceName, "personId", personId);
        }
    }

    @Override
    public List<SpaceUser> findAllBySpaceId(Long spaceId) {
        return spaceUserRepository.findAllBySpaceId(spaceId);
    }

    @Override
    public SpaceUser findById(Long id) {
        return spaceUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public List<SpaceUserView> getAllBySpaceId(Long spaceId) {
        return findAllBySpaceId(spaceId).stream()
                .map(spaceUserMapper::mapToView).toList();
    }

    @Override
    public SpaceUserView create(Long spaceId, Long workspaceUserId) {
        checkByExistsBySpaceIdAndPersonId(spaceId, workspaceUserId);

        Space space = spaceService.findById(spaceId);
        WorkspaceUser workspaceUser = workspaceUserService.findById(workspaceUserId);

        SpaceUser spaceUser = new SpaceUser(space, workspaceUser.getPerson());

        return spaceUserMapper.mapToView(save(spaceUser));
    }

    @Override
    public void deleteById(Long id) {
        if (!spaceUserRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        spaceUserRepository.deleteById(id);
    }
}
