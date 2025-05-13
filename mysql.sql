CREATE TABLE `TIPO_HABITACION` (
  `tipo_id`       INT            NOT NULL AUTO_INCREMENT,
  `nombre_tipo`   VARCHAR(50)    NOT NULL,
  `descripcion`   TEXT,
  `precio_noche`  DECIMAL(10,2)  NOT NULL,
  PRIMARY KEY (`tipo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `CLIENTE` (
  `cliente_id`     CHAR(36)      NOT NULL
                      PRIMARY KEY
                      DEFAULT (UUID()),
  `nombre`         VARCHAR(50)   NOT NULL,
  `apellido`       VARCHAR(50)   NOT NULL,
  `email`          VARCHAR(100),
  `telefono`       VARCHAR(20),
  `fecha_registro` DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY `uk_cliente_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `USUARIO` (
  `usuario_id`    CHAR(36)      NOT NULL
                     PRIMARY KEY
                     DEFAULT (UUID()),
  `usuario`       VARCHAR(50)   NOT NULL,
  `clave_hash`    VARCHAR(255)  NOT NULL,
  `rol`           VARCHAR(20)   NOT NULL,
  UNIQUE KEY `uk_usuario_login` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `HABITACION` (
  `habitacion_id`  INT           NOT NULL AUTO_INCREMENT,
  `numero`         VARCHAR(10)   NOT NULL,
  `piso`           INT           NOT NULL,
  `tipo_id`        INT           NOT NULL,
  `estado`         VARCHAR(20)   NOT NULL,
  PRIMARY KEY (`habitacion_id`),
  KEY `fk_habitacion_tipo` (`tipo_id`),
  CONSTRAINT `fk_habitacion_tipo`
    FOREIGN KEY (`tipo_id`)
    REFERENCES `TIPO_HABITACION`(`tipo_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `RESERVA` (
  `reserva_id`      INT           NOT NULL AUTO_INCREMENT,
  `cliente_id`      CHAR(36)      NOT NULL,
  `habitacion_id`   INT           NOT NULL,
  `usuario_id`      CHAR(36)      NOT NULL,
  `fecha_inicio`    DATE          NOT NULL,
  `fecha_fin`       DATE          NOT NULL,
  `fecha_reserva`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado_reserva`  VARCHAR(20)   NOT NULL,
  `numero_huespedes` INT          NOT NULL,
  PRIMARY KEY (`reserva_id`),
  KEY `fk_reserva_cliente`    (`cliente_id`),
  KEY `fk_reserva_habitacion` (`habitacion_id`),
  KEY `fk_reserva_usuario`    (`usuario_id`),
  CONSTRAINT `fk_reserva_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `CLIENTE`(`cliente_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  CONSTRAINT `fk_reserva_habitacion`
    FOREIGN KEY (`habitacion_id`)
    REFERENCES `HABITACION`(`habitacion_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  CONSTRAINT `fk_reserva_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `USUARIO`(`usuario_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `PAGO` (
  `pago_id`      INT           NOT NULL AUTO_INCREMENT,
  `reserva_id`   INT           NOT NULL,
  `fecha_pago`   DATETIME      NOT NULL,
  `monto`        DECIMAL(10,2) NOT NULL,
  `metodo_pago`  VARCHAR(30)   NOT NULL,
  `estado_pago`  VARCHAR(20)   NOT NULL,
  PRIMARY KEY (`pago_id`),
  KEY `fk_pago_reserva` (`reserva_id`),
  CONSTRAINT `fk_pago_reserva`
    FOREIGN KEY (`reserva_id`)
    REFERENCES `RESERVA`(`reserva_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
