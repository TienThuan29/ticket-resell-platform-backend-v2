package swp391.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import swp391.notificationservice.configuration.MessageConfiguration;
import swp391.notificationservice.dto.request.NotificationRequest;
import swp391.notificationservice.dto.response.ApiResponse;
import swp391.notificationservice.dto.response.NotificationFeign;
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
    public Boolean sendCancelOrderNotification(NotificationRequest notiRequest) {
        boolean flag = true;
        try {
            notificationRepository.save(
                    notificationMapper.toCancelOrderNotification(notiRequest)
            );
        }
        catch (Exception ex) {
            log.info(ex.toString());
            flag = false;
        }
        return flag;
    }


    @Override
    public List<NotificationFeign> getAllNotificationOfReceiver(Long receiverId) {
        return notificationRepository.getNotificationByReceiverId(receiverId)
                .stream().map(notificationMapper::toResponse).toList();
    }

    @Override
    public ApiResponse<?> markReadNotification(String ojectIdString) {
        ObjectId id = new ObjectId(ojectIdString);
        try {
            var notification = notificationRepository.findById(id).orElseThrow(null);
            notification.setIsRead(Boolean.TRUE);
            notificationRepository.save(notification);
        }
        catch (NullPointerException ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_MARK_READ_NOTIFICATION, null
            );
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_MARK_READ_NOTIFICATION, null
        );
    }

    @Override
    public ApiResponse<?> markDeletedNotification(String ojectIdString) {
        ObjectId id = new ObjectId(ojectIdString);
        try {
            var notification = notificationRepository.findById(id).orElseThrow(null);
            notification.setIsDeleted(Boolean.TRUE);
            notificationRepository.save(notification);
        }
        catch (NullPointerException ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_MARK_DELETED_NOTIFICATION, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_MARK_DELETED_NOTIFICATION, null);
    }

    @Override
    public ApiResponse<?> deleteForever(String ojectIdString) {
        ObjectId id = new ObjectId(ojectIdString);
        try {
            var notification = notificationRepository.findById(id).orElseThrow(null);
            notificationRepository.delete(notification);
        }
        catch (NullPointerException ex) {
            log.info(ex.toString());
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, messageConfig.ERROR_DELETE_FOREVER_NOTIFICATION, null);
        }
        return new ApiResponse<>(HttpStatus.OK, messageConfig.SUCCESS_DELETE_FOREVER_NOTIFICATION, null);
    }

    @Override
    public ApiResponse<Boolean> haveNotification(Long receiverId) {
        List<Notification> notifications = notificationRepository.findByReceiverIdAndIsReadAndIsDeleted(receiverId,false, false);
        return new ApiResponse<>(HttpStatus.OK, "", !notifications.isEmpty());
    }

}
