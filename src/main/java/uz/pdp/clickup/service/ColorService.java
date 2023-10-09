package uz.pdp.clickup.service;

import uz.pdp.clickup.entity.Color;

public interface ColorService {
    void create(String name, String hexCode);
    Color findById(Long id);
}
