CREATE TABLE pagamentos (
    id SERIAL PRIMARY KEY NOT NULL,
    valor DECIMAL NOT NULL,
    nome VARCHAR(100) NULL,
    numero VARCHAR(19) NULL,
    expiracao VARCHAR(7) NULL,
    codigo VARCHAR(3) NULL,
    status VARCHAR(255) NOT NULL,
    pedido_id BIGINT NOT NULL,
    forma_de_pagamento_id BIGINT NOT NULL
);