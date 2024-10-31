package com.wisecrm.notificationservice.service;

import com.wisecrm.notificationservice.dto.*;

public interface INotificationControl {
    void send(NotificationDto notificationDto);
}
