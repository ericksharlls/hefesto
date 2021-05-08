USE webhefesto ;
-- -----------------------------------------------------
-- Table `webhefesto`.`tipo_pessoa`
-- -----------------------------------------------------
INSERT INTO tipo_pessoa (nome_tipo_pessoa, descricao_tipo_pessoa) VALUES 
('Docente','Pessoa que é um docente'),
('Tecnico-Administrativo','Pessoa que é um técnico administrativo'),
('Terceirizado','Pessoa terceirizada'),
('Bolsista','Aluno bolsista');

-- -----------------------------------------------------
-- Table `webhefesto`.`predio
-- -----------------------------------------------------
INSERT INTO predio (nome, descricao)
VALUES('CT','Centro de Tecnologia'), ('CTEC','Complexo Tecnológico de Engenharia'), ('Setor IV','Setor de aulas IV');

-- -----------------------------------------------------
-- Table `webhefesto`.`setor`
-- -----------------------------------------------------
INSERT INTO unidade(`codigo_unidade`,`nome_unidade`,`descricao_unidade`, faz_atendimento, unidade_custo) VALUES 
('101','Departamento de TI','Departamento de Tecnologia da Informação', true, true);

-- -----------------------------------------------------
-- Table `webhefesto`.`pessoa`
-- -----------------------------------------------------
INSERT INTO pessoa (nome, email, numero_documento, telefone, data_nascimento, id_tipo_pessoa, id_unidade_lotacao, id_unidade_localizacao) VALUES 
('Admin do hefesto', 'admin@ct.ufrn.br', '01254120164', '84995687451', '2002-05-25', 2, 1, 1);


-- -----------------------------------------------------
-- Table `db_hefesto`.`usuario`
-- -----------------------------------------------------
INSERT INTO usuario (login_usuario, senha_usuario, ativo_usuario, tentativas_login_usuario, id_pessoa) VALUES 
('admin', '$2a$10$j/VoPisL48Ee8UNLbxdh/u8Kh/AGSOHcHXYTpK3GIBiAWjXxFjio6', true, 0, 1);

-- -----------------------------------------------------
-- Table `db_hefesto`.`papel`
-- -----------------------------------------------------
INSERT INTO papel_usuario (nome_papel, descricao_papel) VALUES 
('ADMINISTRADOR', 'Papel para administradores do sistema'),
('GESTOR_SETOR', 'Papel para gestores de setor'),
('TECNICO_ATENDIMENTO', 'Papel para tecnicos de atendimento de setor');
  
-- -----------------------------------------------------
-- Table `db_hefesto`.`permissao_usuario`
-- -----------------------------------------------------
INSERT INTO permissao_usuario (id_papel, id_usuario) VALUES 
(1, 1);

-- -----------------------------------------------------
-- Table `db_hefesto`.`status`
-- -----------------------------------------------------
insert into status values (1,'Aberto'),(2,'Em execução'),(3,'Parado'),(4,'Concluído');

-- -----------------------------------------------------
-- Table `db_hefesto`.`operacao_chamado`
-- -----------------------------------------------------
insert into operacao_chamado values (1, 'Cadastrar'), (2, 'Editar'), (3, 'Comentar'), (4, 'Mudar Status'), (5, 'Finalizar');
