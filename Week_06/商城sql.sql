DROP TABLE User;
CREATE TABLE User(
    UUId BIGINT NOT NULL   COMMENT '用户Id 用户ID' ,
    nickname VARCHAR(32)    COMMENT '用户昵称 用户昵称' ,
    CREATED_TIME DATETIME    COMMENT '创建时间 创建时间' ,
    PassWord VARCHAR(32)    COMMENT '密码 密码' ,
    birthday DATE    COMMENT '生日 生日' ,
    address VARCHAR(32)    COMMENT '家庭住址' ,
    PRIMARY KEY (UUId)
) COMMENT = 'user 商城用户';

ALTER TABLE User COMMENT 'user';

DROP TABLE commodity;
CREATE TABLE commodity(
    UUID BIGINT    COMMENT 'UUID' ,
    type VARCHAR(32)    COMMENT '商品类型 商品类型' ,
    Price DECIMAL(32,8)    COMMENT '金额 金额' ,
    UPDATED_BY BIGINT    COMMENT '订单号 订单号' 
) COMMENT = '商品表 ';

ALTER TABLE commodity COMMENT '商品表';
