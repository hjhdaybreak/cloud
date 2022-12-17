package com.bee.service;

public interface IMessageProvider {

    String send(String msg);

    String groupSend(String msg);
}
