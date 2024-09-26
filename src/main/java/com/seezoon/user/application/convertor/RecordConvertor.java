package com.seezoon.user.application.convertor;

import com.seezoon.user.application.dto.RecordCO;
import com.seezoon.user.domain.dao.po.RecordPO;

public class RecordConvertor {

    public static RecordCO toCO(RecordPO po) {
        if (po == null) {
            return null;
        }
        RecordCO co = new RecordCO();
        co.setId(po.getId());
        co.setUid(po.getUid());
        co.setType(po.getType());
        co.setSrc(po.getSrc());
        co.setDst(po.getDst());
        co.setStatus(po.getStatus());
        co.setCreateTime(po.getCreateTime());
        return co;
    }
}
