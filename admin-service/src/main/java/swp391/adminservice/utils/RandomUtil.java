package swp391.adminservice.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swp391.adminservice.configuration.ConstantConfiguration;
import swp391.adminservice.repository.StaffRepository;
import java.util.Random;

@RequiredArgsConstructor
@Component("adminServiceRandomUtil")
public class RandomUtil {

    private final StaffRepository staffRepository;

    private final ConstantConfiguration constant;

    public String randomStaffCode() {
        Random random = new Random();
        String staffCode;
        do {
            int number = random.nextInt(90000) + 10000;
            staffCode = constant.STAFF_CODE_PREFIX + number;
        } while (isExistStaffCode(staffCode));
        return staffCode;
    }

    private boolean isExistStaffCode(String staffCode) {
        return staffRepository.findByStaffCode(staffCode).isPresent();
    }

}
