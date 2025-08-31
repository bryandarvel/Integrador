DROP DATABASE IF EXISTS sgcd;

CREATE DATABASE sgcd;

USE sgcd;

CREATE TABLE Usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    senha VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Categoria (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nome_categoria VARCHAR(30)
);

INSERT INTO Categoria (id_categoria, nome_categoria)
VALUES 
    (1, 'Alimentação'), 
    (2, 'Transporte'),
    (3, 'Contas'),
    (4, 'Entretenimento');

CREATE TABLE Metas (
    id_metas INT PRIMARY KEY AUTO_INCREMENT,
    limite DECIMAL(10, 2) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fk_Usuario_id_usuario INT NOT NULL,
    fk_Categoria_id_categoria INT NOT NULL,
    FOREIGN KEY (fk_Usuario_id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (fk_Categoria_id_categoria) REFERENCES Categoria(id_categoria) ON DELETE CASCADE
);

CREATE TABLE Despesas (
    id_despesas INT PRIMARY KEY AUTO_INCREMENT,
    valor DECIMAL(10, 2) NOT NULL,
    descricao_despesas VARCHAR(50),
    data_despesa TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fk_Usuario_id_usuario INT NOT NULL,
    fk_Categoria_id_categoria INT NOT NULL,
    FOREIGN KEY (fk_Usuario_id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (fk_Categoria_id_categoria) REFERENCES Categoria(id_categoria) ON DELETE CASCADE
);
