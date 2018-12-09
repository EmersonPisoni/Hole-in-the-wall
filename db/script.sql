CREATE TABLE admin (
    id   NUMBER(19) NOT NULL
);

CREATE TABLE muro (
    id                NUMBER(19) NOT NULL,
    imagem_muro       VARCHAR2(255) NOT NULL,
    imagem_gabarito   VARCHAR2(255) NOT NULL 
);

ALTER TABLE muro ADD CONSTRAINT muro_pk PRIMARY KEY ( id );

CREATE TABLE partida (
    id                  NUMBER(19) NOT NULL,
    dificuldade         VARCHAR2(255) NOT NULL,
    pontos              NUMBER(19) NOT NULL,
    data_hora_partida   DATE NOT NULL,
    usuario_id          NUMBER(19) NOT NULL
);

ALTER TABLE partida ADD CONSTRAINT partida_pk PRIMARY KEY ( id );

CREATE TABLE usuario (
    id        NUMBER(19) NOT NULL,
    nome      VARCHAR2(255) NOT NULL,
    senha     VARCHAR2(255) NOT NULL,
    email     VARCHAR2(255) NOT NULL,
    apelido   VARCHAR2(255) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

ALTER TABLE usuario ADD CONSTRAINT uk_usuario_apelido UNIQUE ( apelido );

ALTER TABLE usuario ADD CONSTRAINT uk_usuario_email UNIQUE ( email );

ALTER TABLE admin
    ADD CONSTRAINT admin_usuario_fk FOREIGN KEY ( id )
        REFERENCES usuario ( id );

ALTER TABLE partida
    ADD CONSTRAINT partida_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

--  SEQUENCES

CREATE SEQUENCE seq_muro START WITH 1 NOMAXVALUE INCREMENT BY 1 CACHE 20;

CREATE SEQUENCE seq_partida START WITH 1 NOMAXVALUE INCREMENT BY 1 CACHE 20;

CREATE SEQUENCE seq_usuario START WITH 1 NOMAXVALUE INCREMENT BY 1 CACHE 20;