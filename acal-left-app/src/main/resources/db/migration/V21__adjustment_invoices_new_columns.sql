ALTER TABLE conta
    MODIFY COLUMN paid_by_pix BOOLEAN NULL,
    MODIFY COLUMN paid_with_alternative_bill BOOLEAN NULL;