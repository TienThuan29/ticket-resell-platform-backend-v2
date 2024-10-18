package swp391.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.notificationservice.configuration.MessageConfiguration;
import swp391.notificationservice.dto.request.NotificationRequest;
import swp391.notificationservice.dto.response.ApiResponse;
import swp391.notificationservice.dto.response.NotificationResponse;
import swp391.notificationservice.entity.Notification;
import swp391.notificationservice.mapper.NotificationMapper;
import swp391.notificationservice.repository.NotificationRepository;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final MessageConfiguration messageConfig;


    @Override
    public Boolean sendVerificationNotification(NotificationRequest request) {
        Boolean flag = true;
        try {
            notificationRepository.save(
                    notificationMapper.toVerificationNotification(request)
            );
        }
        catch (Exception ex) {
            log.info(ex.toString());
            flag = false;
        }
        return flag;
    }

    @Override
    public ApiResponse<List<NotificationResponse>> getAllNotificationOfReceiver(Long receiverId) {
        return new ApiResponse<>(HttpStatus.OK, "",
            notificationRepository.getNotificationByReceiverId(receiverId)
                    .stream().map(notificationMapper::toResponse).toList()
        );
    }

}
