ALTER TABLE enderecopessoa RENAME COLUMN SocioExclusivo TO socio_exclusivo;
ALTER TABLE enderecopessoa RENAME COLUMN idEndereco TO id_endereco;
ALTER TABLE enderecopessoa RENAME COLUMN idPessoa TO id_pessoa;
ALTER TABLE enderecopessoa RENAME COLUMN idCategoriaSocio TO id_categoria_socio;


ALTER TABLE conta RENAME COLUMN idEnderecoPessoa TO id_endereco_pessoa;