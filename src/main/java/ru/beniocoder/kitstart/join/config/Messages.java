package ru.beniocoder.kitstart.join.config;

import ru.abstractcoder.benioapi.config.msg.MsgKey;
import ru.abstractcoder.benioapi.config.msg.MsgProperties;

public enum Messages implements MsgKey {

    KICK_MESSAGE(MsgProperties.create("&cПриходите к нам еще!")),
    KIT_SELECTED(MsgProperties.create("&eВы выбрали набор: &e{kit}")),
    CONFIG_RELOAD(MsgProperties.create("&eКонфиг перезагружен"))

    ;
    private final MsgProperties properties;

    Messages(MsgProperties properties) {
        this.properties = properties;
    }

    @Override
    public MsgProperties getProperties() {
        return properties;
    }
}
