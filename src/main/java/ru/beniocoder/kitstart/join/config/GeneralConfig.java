package ru.beniocoder.kitstart.join.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.plugin.Plugin;
import ru.abstractcoder.benioapi.config.HoconConfig;
import ru.abstractcoder.benioapi.database.MySqlConnectionPool;
import ru.beniocoder.kitstart.kit.Kit;

import java.util.List;

public class GeneralConfig extends HoconConfig {

    private ConfigDto dto;

    public GeneralConfig(Plugin plugin, ObjectMapper objectMapper) {
        super(plugin, "settings", true);
        addOnReloadAction(() -> {
            if (dto != null && dto.mysql != null) {
                dto.mysql.close();
            }
            dto = objectMapper.readValue(handle.root().render(JSON_WRITE_DEFAULT), ConfigDto.class);
        });
    }

    public MySqlConnectionPool getMysql() {
        return dto.mysql;
    }

    public List<Kit> getKits() {
        return dto.kits;
    }

    private static final class ConfigDto {
        private final MySqlConnectionPool mysql;
        private final List<Kit> kits;

        @JsonCreator
        public ConfigDto(MySqlConnectionPool mysql, List<Kit> kits) {
            this.mysql = mysql;
            this.kits = kits;
        }
    }

}