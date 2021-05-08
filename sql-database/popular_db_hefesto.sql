USE webhefesto ;
-- -----------------------------------------------------
-- Table `webhefesto`.`tipo_pessoa`
-- -----------------------------------------------------
INSERT INTO tipo_pessoa VALUES 
(1,'Docente','Pessoa que é um docente'),
(2,'Tecnico-Administrativo','Pessoa que é um técnico administrativo'),
(3,'Terceirizado','Pessoa terceirizada'),
(4,'Bolsista','Aluno bolsista');

-- -----------------------------------------------------
-- Table `webhefesto`.`predio
-- -----------------------------------------------------
INSERT INTO webhefesto.predio (nome, descricao)
VALUES('CT','Centro de Tecnologia'), ('CTEC','Complexo Tecnológico de Engenharia'), ('Setor IV','Setor de aulas IV');

-- -----------------------------------------------------
-- Table `webhefesto`.`setor`
-- -----------------------------------------------------
INSERT INTO `webhefesto`.`setor`(`codigo`,`nome`,`descricao`,`id_predio`, faz_atendimento)
VALUES
('101','Departamento de TI','Departamento de Tecnologia da Informação',1,true),
('102','Infraestrutura','Departamento de Infraestrutura',1,true), 
(null,'Secretaria','Secreatria',1,false),
(null,'Secretaria','Secreatria',2,false),
(null,'Secretaria','Secreatria',3,false);

-- -----------------------------------------------------
-- Table `webhefesto`.`tipo_servico`
-- -----------------------------------------------------
INSERT INTO tipo_servico VALUES 
(1, '01', 'Ponto de rede', 'Conserto de ponto', 1),
(2, '01', 'Troca de lâmpada', 'Conserto de lâmpada', 2);

-- -----------------------------------------------------
-- Table `webhefesto`.`pessoa`
-- -----------------------------------------------------
INSERT INTO pessoa (id_setor_lotacao, id_setor_localizacao, nome, email, numero_documento, telefone, data_nascimento, id_tipo_pessoa) VALUES 
(1,1,'Admin do hefesto', 'admin@ct.ufrn.br', '01254120164', '84995687451', '2002-05-25',1),
(1,1,'Gestor de TI', 'ti@ct.ufrn.br', '01254120164', '84995687451', '2002-05-25',1),
(2,2,'Gestor de INFRA', 'ti@ct.ufrn.br', '01254120164', '84995687451', '2002-05-25',1);


-- -----------------------------------------------------
-- Table `db_hefesto`.`usuario`
-- -----------------------------------------------------
INSERT INTO usuario (login_usuario, senha_usuario, ativo_usuario, tentativas_login_usuario, id_pessoa) VALUES 
('admin', '$2a$10$j/VoPisL48Ee8UNLbxdh/u8Kh/AGSOHcHXYTpK3GIBiAWjXxFjio6', true, 0, 1),
('gestor_ti', '$2a$10$j/VoPisL48Ee8UNLbxdh/u8Kh/AGSOHcHXYTpK3GIBiAWjXxFjio6', true, 0, 2),
('gestor_infra', '$2a$10$j/VoPisL48Ee8UNLbxdh/u8Kh/AGSOHcHXYTpK3GIBiAWjXxFjio6', true, 0, 3);

-- -----------------------------------------------------
-- Table `db_hefesto`.`papel`
-- -----------------------------------------------------
INSERT INTO papel_usuario (nome_papel, descricao_papel) VALUES 
('ADMINISTRADOR', 'Papel para administradores do sistema'),
('GESTOR_SETOR', 'Papel para gestores do setor');
  
-- -----------------------------------------------------
-- Table `db_hefesto`.`permissao_usuario`
-- -----------------------------------------------------
INSERT INTO permissao_usuario (id_papel, id_usuario) VALUES 
(1, 1),
(2, 2),
(2, 3);

-- -----------------------------------------------------
-- Table `db_hefesto`.`status`
-- -----------------------------------------------------
insert into status values (1,'Aberto'),(2,'Em execução'),(3,'Parado'),(4,'Concluido');

-- -----------------------------------------------------
-- Table `db_hefesto`.`operacao_chamado`
-- -----------------------------------------------------
insert into operacao_chamado values (1, 'Cadastrar'), (2, 'Editar'), (3, 'Comentar'), (4, 'Mudar Status'), (5, 'Finalizar');
