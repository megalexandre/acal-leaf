CREATE INDEX idx_user_login ON user_model (username, password);
CREATE INDEX idx_pessoa_nome_sobrenome_id ON pessoa (nome, sobrenome, id);
CREATE INDEX idx_categoriasocio_nome ON categoriasocio (nome);