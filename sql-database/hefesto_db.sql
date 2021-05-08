-- MySQL Script generated by MySQL Workbench
-- Wed Apr 29 19:19:11 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema webhefesto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema webhefesto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webhefesto` DEFAULT CHARACTER SET utf8 ;
USE `webhefesto` ;

-- -----------------------------------------------------
-- Table `webhefesto`.`tipo_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`tipo_pessoa` (
  `id_tipo_pessoa` TINYINT NOT NULL AUTO_INCREMENT,
  `nome_tipo_pessoa` VARCHAR(30) NOT NULL,
  `descricao_tipo_pessoa` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_tipo_pessoa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`unidade` (
  `id_unidade` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo_unidade` VARCHAR(10) NULL DEFAULT NULL,
  `nome_unidade` VARCHAR(200) NOT NULL,
  `descricao_unidade` VARCHAR(200) NOT NULL,
  `sigla_unidade` VARCHAR(45) NULL,
  `faz_atendimento` TINYINT NULL DEFAULT NULL,
  `unidade_custo` TINYINT NOT NULL,
  `id_unidade_ufrn` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id_unidade`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`pessoa` (
  `id_pessoa` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `email` VARCHAR(40) NULL DEFAULT NULL,
  `numero_documento` VARCHAR(11) NULL DEFAULT NULL,
  `matricula` VARCHAR(13) NULL DEFAULT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `data_nascimento` DATE NULL DEFAULT NULL,
  `id_pessoa_ufrn` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  `id_tipo_pessoa` TINYINT NOT NULL,
  `id_unidade_lotacao` SMALLINT UNSIGNED NOT NULL,
  `id_unidade_localizacao` SMALLINT UNSIGNED NULL,
  PRIMARY KEY (`id_pessoa`),
  INDEX `fk_pessoa_tipo_pessoa1_idx` (`id_tipo_pessoa` ASC) VISIBLE,
  INDEX `fk_pessoa_unidade1_idx` (`id_unidade_lotacao` ASC) VISIBLE,
  INDEX `fk_pessoa_unidade2_idx` (`id_unidade_localizacao` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_tipo_pessoa1`
    FOREIGN KEY (`id_tipo_pessoa`)
    REFERENCES `webhefesto`.`tipo_pessoa` (`id_tipo_pessoa`),
  CONSTRAINT `fk_pessoa_unidade1`
    FOREIGN KEY (`id_unidade_lotacao`)
    REFERENCES `webhefesto`.`unidade` (`id_unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_unidade2`
    FOREIGN KEY (`id_unidade_localizacao`)
    REFERENCES `webhefesto`.`unidade` (`id_unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`tipo_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`tipo_servico` (
  `id_tipo_servico` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo_tipo_servico` VARCHAR(3) NOT NULL,
  `nome_tipo_servico` VARCHAR(45) NOT NULL,
  `descricao_tipo_servico` VARCHAR(70) NOT NULL,
  `id_tipo_servico_pai` TINYINT UNSIGNED NULL,
  `id_unidade` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_tipo_servico`),
  INDEX `fk_tipo_servico_tipo_servico1_idx` (`id_tipo_servico_pai` ASC) VISIBLE,
  INDEX `fk_tipo_servico_unidade1_idx` (`id_unidade` ASC) VISIBLE,
  CONSTRAINT `fk_tipo_servico_tipo_servico1`
    FOREIGN KEY (`id_tipo_servico_pai`)
    REFERENCES `webhefesto`.`tipo_servico` (`id_tipo_servico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_servico_unidade1`
    FOREIGN KEY (`id_unidade`)
    REFERENCES `webhefesto`.`unidade` (`id_unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`status` (
  `id_status` TINYINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_status`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`predio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`predio` (
  `id_predio` TINYINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`id_predio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`sala` (
  `id_sala` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome_sala` VARCHAR(45) NOT NULL,
  `descricao_sala` VARCHAR(70) NOT NULL,
  `id_predio` TINYINT NOT NULL,
  `id_unidade` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_sala`),
  INDEX `fk_sala_predio1_idx` (`id_predio` ASC) VISIBLE,
  INDEX `fk_sala_unidade1_idx` (`id_unidade` ASC) VISIBLE,
  CONSTRAINT `fk_sala_predio1`
    FOREIGN KEY (`id_predio`)
    REFERENCES `webhefesto`.`predio` (`id_predio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sala_unidade1`
    FOREIGN KEY (`id_unidade`)
    REFERENCES `webhefesto`.`unidade` (`id_unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webhefesto`.`chamado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`chamado` (
  `id_chamado` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(12) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `materiais` VARCHAR(200) NULL DEFAULT NULL,
  `observacoes` VARCHAR(200) NULL DEFAULT NULL,
  `valor_servico` FLOAT NULL DEFAULT NULL,
  `data_abertura` DATE NOT NULL,
  `data_conclusao` DATE NULL DEFAULT NULL,
  `id_tipo_servico` TINYINT UNSIGNED NOT NULL,
  `id_status` TINYINT NOT NULL,
  `id_unidade` SMALLINT UNSIGNED NOT NULL,
  `id_solicitante` MEDIUMINT UNSIGNED NOT NULL,
  `id_sala` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_chamado`),
  INDEX `fk_chamado_servico1_idx` (`id_tipo_servico` ASC) VISIBLE,
  INDEX `fk_chamado_status1_idx` (`id_status` ASC) VISIBLE,
  INDEX `fk_chamado_unidade1_idx` (`id_unidade` ASC) VISIBLE,
  INDEX `fk_chamado_pessoa1_idx` (`id_solicitante` ASC) VISIBLE,
  INDEX `fk_chamado_sala1_idx` (`id_sala` ASC) VISIBLE,
  CONSTRAINT `fk_chamado_servico1`
    FOREIGN KEY (`id_tipo_servico`)
    REFERENCES `webhefesto`.`tipo_servico` (`id_tipo_servico`),
  CONSTRAINT `fk_chamado_status1`
    FOREIGN KEY (`id_status`)
    REFERENCES `webhefesto`.`status` (`id_status`),
  CONSTRAINT `fk_chamado_unidade1`
    FOREIGN KEY (`id_unidade`)
    REFERENCES `webhefesto`.`unidade` (`id_unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chamado_pessoa1`
    FOREIGN KEY (`id_solicitante`)
    REFERENCES `webhefesto`.`pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chamado_sala1`
    FOREIGN KEY (`id_sala`)
    REFERENCES `webhefesto`.`sala` (`id_sala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`atendimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`atendimento` (
  `id_atendimento` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_pessoa` MEDIUMINT UNSIGNED NOT NULL,
  `id_chamado` MEDIUMINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_atendimento`, `id_pessoa`, `id_chamado`),
  INDEX `fk_atendimento_pessoa1_idx` (`id_pessoa` ASC) VISIBLE,
  INDEX `fk_atendimento_chamado1_idx` (`id_chamado` ASC) VISIBLE,
  CONSTRAINT `fk_atendimento_pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `webhefesto`.`pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atendimento_chamado1`
    FOREIGN KEY (`id_chamado`)
    REFERENCES `webhefesto`.`chamado` (`id_chamado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`operacao_chamado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`operacao_chamado` (
  `id_operacao_chamado` INT NOT NULL AUTO_INCREMENT,
  `nome_operacao_chamado` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_operacao_chamado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`historico_chamado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`historico_chamado` (
  `id_historico_chamado` INT NOT NULL AUTO_INCREMENT,
  `hora_realizacao` DATETIME NULL DEFAULT NULL,
  `id_operacao_chamado` INT NOT NULL,
  `id_chamado` MEDIUMINT UNSIGNED NOT NULL,
  `id_pessoa` MEDIUMINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_historico_chamado`),
  INDEX `fk_historico_chamado_operacao_chamado1_idx` (`id_operacao_chamado` ASC) VISIBLE,
  INDEX `fk_historico_chamado_chamado1_idx` (`id_chamado` ASC) VISIBLE,
  INDEX `fk_historico_chamado_pessoa1_idx` (`id_pessoa` ASC) VISIBLE,
  CONSTRAINT `fk_historico_chamado_operacao_chamado1`
    FOREIGN KEY (`id_operacao_chamado`)
    REFERENCES `webhefesto`.`operacao_chamado` (`id_operacao_chamado`),
  CONSTRAINT `fk_historico_chamado_chamado1`
    FOREIGN KEY (`id_chamado`)
    REFERENCES `webhefesto`.`chamado` (`id_chamado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historico_chamado_pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `webhefesto`.`pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`comentario_chamado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`comentario_chamado` (
  `id_comentario_chamado` INT NOT NULL AUTO_INCREMENT,
  `texto_comentario_chamado` VARCHAR(100) NOT NULL,
  `id_historico_chamado` INT NOT NULL,
  PRIMARY KEY (`id_comentario_chamado`),
  INDEX `fk_comentario_chamado_historico_chamado1_idx` (`id_historico_chamado` ASC) VISIBLE,
  CONSTRAINT `fk_comentario_chamado_historico_chamado1`
    FOREIGN KEY (`id_historico_chamado`)
    REFERENCES `webhefesto`.`historico_chamado` (`id_historico_chamado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`papel_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`papel_usuario` (
  `id_papel` TINYINT NOT NULL AUTO_INCREMENT,
  `nome_papel` VARCHAR(20) NOT NULL,
  `descricao_papel` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_papel`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`usuario` (
  `id_usuario` SMALLINT NOT NULL AUTO_INCREMENT,
  `login_usuario` VARCHAR(20) NOT NULL,
  `senha_usuario` VARCHAR(200) NOT NULL,
  `ativo_usuario` TINYINT NOT NULL,
  `tentativas_login_usuario` TINYINT NULL DEFAULT NULL,
  `id_pessoa` MEDIUMINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `login_usuario_UNIQUE` (`login_usuario` ASC) VISIBLE,
  INDEX `fk_usuario_pessoa1_idx` (`id_pessoa` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `webhefesto`.`pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`permissao_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`permissao_usuario` (
  `id_papel` TINYINT NOT NULL,
  `id_usuario` SMALLINT NOT NULL,
  PRIMARY KEY (`id_papel`, `id_usuario`),
  INDEX `fk_papel_has_usuario_usuario1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_papel_has_usuario_papel1_idx` (`id_papel` ASC) VISIBLE,
  CONSTRAINT `fk_papel_has_usuario_papel1`
    FOREIGN KEY (`id_papel`)
    REFERENCES `webhefesto`.`papel_usuario` (`id_papel`),
  CONSTRAINT `fk_papel_has_usuario_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `webhefesto`.`usuario` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webhefesto`.`historico_mudanca_status_chamado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webhefesto`.`historico_mudanca_status_chamado` (
  `id_historico_mudanca_status` INT NOT NULL AUTO_INCREMENT,
  `id_status` TINYINT NOT NULL,
  `id_historico_chamado` INT NOT NULL,
  PRIMARY KEY (`id_historico_mudanca_status`),
  INDEX `fk_historico_mudanca_status_chamado_status_idx` (`id_status` ASC) VISIBLE,
  INDEX `fk_historico_mudanca_status_chamado_historico_chamado1_idx` (`id_historico_chamado` ASC) VISIBLE,
  CONSTRAINT `fk_historico_mudanca_status_chamado_status`
    FOREIGN KEY (`id_status`)
    REFERENCES `webhefesto`.`status` (`id_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historico_mudanca_status_chamado_historico_chamado1`
    FOREIGN KEY (`id_historico_chamado`)
    REFERENCES `webhefesto`.`historico_chamado` (`id_historico_chamado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
