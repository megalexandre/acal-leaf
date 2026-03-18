CREATE INDEX idx_invoice_period_desc
    ON conta (data_referente DESC, id_endereco_pessoa);
CREATE INDEX idx_end_pessoa_fk ON enderecopessoa (id_pessoa, id_endereco, id_categoria_socio);
CREATE INDEX idx_hidrometro_conta ON hidrometro (idconta);
CREATE INDEX idx_address_type_name ON endereco (tipo, nome);




























