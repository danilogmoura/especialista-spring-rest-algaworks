alter table forma_pagamento
    add data_atualizacao datetime null;

update forma_pagamento
    set data_atualizacao = utc_timestamp;

alter table forma_pagamento
    modify data_atualizacao datetime not null;

alter table forma_pagamento
    add data_cadastro datetime null;

update forma_pagamento
    set data_cadastro = utc_timestamp;

alter table forma_pagamento
    modify data_cadastro datetime not null;

