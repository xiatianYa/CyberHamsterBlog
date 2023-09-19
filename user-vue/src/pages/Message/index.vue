<template>
    <div class="main">
        <div class="message">
            <div class="title">
                <p class="name">
                    留言墙
                </p>
                <p class="slogan">
                    很多事情值得记录,当然也值得回味
                </p>
            </div>
            <div class="label">
                <div class="label-li" :class="queryParam.type === -1 ? 'select' : ''" @click="changeParamType(-1)">
                    全部
                </div>
                <div class="label-li" :class="queryParam.type === 0 ? 'select' : ''" @click="changeParamType(0)">
                    留言
                </div>
                <div class="label-li" :class="queryParam.type === 1 ? 'select' : ''" @click="changeParamType(1)">
                    目标
                </div>
                <div class="label-li" :class="queryParam.type === 2 ? 'select' : ''" @click="changeParamType(2)">
                    理想
                </div>
                <div class="label-li" :class="queryParam.type === -3 ? 'select' : ''" @click="changeParamType(3)">
                    过去
                </div>
                <div class="label-li" :class="queryParam.type === 4 ? 'select' : ''" @click="changeParamType(4)">
                    将来
                </div>
                <div class="label-li" :class="queryParam.type === 5 ? 'select' : ''" @click="changeParamType(5)">
                    爱情
                </div>
                <div class="label-li" :class="queryParam.type === 6 ? 'select' : ''" @click="changeParamType(6)">
                    亲情
                </div>
                <div class="label-li" :class="queryParam.type === 7 ? 'select' : ''" @click="changeParamType(7)">
                    友情
                </div>
                <div class="label-li" :class="queryParam.type === 8 ? 'select' : ''" @click="changeParamType(8)">
                    秘密
                </div>
                <div class="label-li" :class="queryParam.type === 9 ? 'select' : ''" @click="changeParamType(9)">
                    信条
                </div>
                <div class="label-li" :class="queryParam.type === 10 ? 'select' : ''" @click="changeParamType(10)">
                    无题
                </div>
            </div>
            <div class="note" @scroll="scrollEvent">
                <div class="note-li note-card" :style="message.messageColor" v-for="message, index in messageList">
                    <div class="note-top">
                        <div class="time">{{ message.createTime }}</div>
                        <div class="note-label">{{ message.type }}</div>
                    </div>
                    <p class="note-content" @click="openliuyAndliuy(message)">
                        {{ message.messageContent }}
                    </p>
                    <div class="note-footer">
                        <div class="note-ifm">
                            <p class="like">
                                <span class="iconfont icon-xiai" @click="AddLike(message.messageId)">
                                    <span>{{ message.messageLike }}</span>
                                </span>
                            </p>
                            <div class="comment">
                                <span class="iconfont icon-xinxifill"></span>
                                <span>{{ message.replyList.length  }}</span>
                            </div>
                        </div>
                        <div class="note-name">
                            {{ message.messageName }}
                        </div>
                    </div>
                </div>
            </div>
            <div class="yk-model" :class="liuye ? 'transfrom-X' : 'transfromX'">
                <div class="modal-inner">
                    <div class="model-topbar">
                        <div class="title">留言</div>
                        <i class="iconfont icon-guanbi" @click="closeliuye()"></i>
                    </div>
                    <div class="model-slot">
                        <div class="create">
                            <div class="colors">
                                <p class="color" :class="colorIndex === 0 ? 'selectcolor' : ''"
                                    style="background-color: rgb(252, 175, 162);"
                                    @click="changeColor('background-color: rgb(252, 175, 162);', 0)"></p>
                                <p class="color" :class="colorIndex === 1 ? 'selectcolor' : ''"
                                    style="background-color: rgb(255, 227, 148);"
                                    @click="changeColor('background-color: rgb(255, 227, 148);', 1)"></p>
                                <p class="color" :class="colorIndex === 2 ? 'selectcolor' : ''"
                                    style="background-color: rgb(146, 230, 245);"
                                    @click="changeColor('background-color: rgb(146, 230, 245);', 2)"></p>
                                <p class="color" :class="colorIndex === 3 ? 'selectcolor' : ''"
                                    style="background-color: rgb(168, 237, 138);"
                                    @click="changeColor('background-color: rgb(168, 237, 138);', 3)"></p>
                                <p class="color" :class="colorIndex === 4 ? 'selectcolor' : ''"
                                    style="background-color: rgb(202, 167, 247);"
                                    @click="changeColor('background-color: rgb(202, 167, 247);', 4)"></p>
                            </div>
                            <div class="card" style="background-color: rgba(252,175,162,0.3)">
                                <textarea class="ly" placeholder="留言..." maxlength="96"
                                    v-model="liuyCommitMenu.messageContent"></textarea>
                                <input type="text" class="qm" placeholder="签名" placeholder-class="qmpl"
                                    v-model="liuyCommitMenu.messageName">
                            </div>
                            <div>
                                <p class="title">选择标签</p>
                                <div class="labels">
                                    <p :class="liuyCommitMenu.messageType === 0 ? 'labelslected' : ''"
                                        @click="changeLabel(0)">留言</p>
                                    <p :class="liuyCommitMenu.messageType === 1 ? 'labelslected' : ''"
                                        @click="changeLabel(1)">目标</p>
                                    <p :class="liuyCommitMenu.messageType === 2 ? 'labelslected' : ''"
                                        @click="changeLabel(2)">理想</p>
                                    <p :class="liuyCommitMenu.messageType === 3 ? 'labelslected' : ''"
                                        @click="changeLabel(3)">过去</p>
                                    <p :class="liuyCommitMenu.messageType === 4 ? 'labelslected' : ''"
                                        @click="changeLabel(4)">将来</p>
                                    <p :class="liuyCommitMenu.messageType === 5 ? 'labelslected' : ''"
                                        @click="changeLabel(5)">爱情</p>
                                    <p :class="liuyCommitMenu.messageType === 6 ? 'labelslected' : ''"
                                        @click="changeLabel(6)">亲情</p>
                                    <p :class="liuyCommitMenu.messageType === 7 ? 'labelslected' : ''"
                                        @click="changeLabel(7)">友情</p>
                                    <p :class="liuyCommitMenu.messageType === 8 ? 'labelslected' : ''"
                                        @click="changeLabel(8)">秘密</p>
                                    <p :class="liuyCommitMenu.messageType === 9 ? 'labelslected' : ''"
                                        @click="changeLabel(9)">信条</p>
                                    <p :class="liuyCommitMenu.messageType === 10 ? 'labelslected' : ''"
                                        @click="changeLabel(10)">无题</p>
                                </div>
                            </div>
                            <div>
                                <p class="title">免责声明</p>
                                <p class="mzsm">不想皮燕子被打掉,就好好留言!!!!
                                    <br />
                                    1、反对宪法所确定的基本原则的；
                                    <br />
                                    2、危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家 统一的；
                                    <br />
                                    3、损害国家荣誉和利益的；
                                    <br />
                                    4、煽动民族仇恨、民族歧视，破坏民族团结的；
                                    <br />
                                    5、破坏国家宗教政策，宣扬邪教和封建迷信的；
                                    <br />
                                    6、散布谣言，扰乱社会秩序，破坏社会稳定的；
                                    <br />
                                    8、侮辱或者诽谤他人，侵害他人合法权益的；
                                    <br />
                                    9、含有法律、行政法规禁止的其他内容的信息。
                                </p>
                            </div>
                            <div class="footbt">
                                <button class="lg secondary yk-button fq" @click="closeliuye()">放弃</button>
                                <button class="lg primary yk-button qd" @click="addMessage()">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="yk-model" :class="liuyAndliuy ? 'transfrom-X' : 'transfromX'">
                <div class="modal-inner">
                    <div class="model-topbar">
                        <p class="title">详细</p>
                        <i class="iconfont icon-guanbi" @click="closeliuyAndliuy()"></i>
                    </div>
                    <div class="model-slot">
                        <div class="details">
                            <div class="fback">
                                <div class="report" @click="addMessageReport(liuyMenu.messageId)">举报</div>
                            </div>
                            <div class="note-li" style="background:rgba(146,230,245,0.3);width:100%">
                                <div class="note-top">
                                    <p class="time">{{ liuyMenu.createTime }}</p>
                                    <p class="note-label">{{ liuyMenu.type }}</p>
                                </div>
                                <div class="note-content">{{ liuyMenu.messageContent }}</div>
                                <div class="note-footer">
                                    <div class="note-ifm">
                                        <p class="like">
                                            <span class="iconfont icon-xiai"></span>
                                            <span>{{ liuyMenu.messageLike }}</span>
                                        </p>
                                        <p class="comment">
                                            <span class="iconfont icon-xinxifill"></span>
                                            <span>{{ liuyMenu.replyList.length }}</span>
                                        </p>
                                    </div>
                                    <div class="note-name">{{ liuyMenu.messageName }}</div>
                                </div>
                            </div>
                            <div class="form">
                                <textarea class="comment" placeholder="请输入..." maxlength="96"
                                    v-model="liuyAndliuyCommitMenu.replyContent"></textarea>
                                <div class="form-bt">
                                    <input class="input qm" placeholder="签名" v-model="liuyAndliuyCommitMenu.replyName">
                                    <button class="base primary yk-button" @click="addReply()">评论</button>
                                </div>
                            </div>
                            <div>
                                <p class="title">评论 {{ liuyMenu.replyList.length }}</p>
                                <div class="comment" v-for="reply, index in liuyMenu.replyList">
                                    <div>
                                        <img class="user" :src="reply.replyAvatar" />
                                    </div>
                                    <div class="left">
                                        <div class="names">
                                            <p class="name">{{ reply.replyName }}</p>
                                            <p class="time">{{ reply.createTime }}</p>
                                        </div>
                                        <p class="coment-m">{{ reply.replyContent }}</p>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="loading" v-show="load">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
            <div class="add" @click="openliuye()">
                <span class="iconfont">
                    <div class="line1"></div>
                    <div class="line2"></div>
                </span>
            </div>

        </div>
    </div>
</template>

<script>
import { reqGetMessageList, reqGetinsertMessage, reqGetinsertReply, reqGetAddLike, reqGetAddMessageReport } from "@/api";
export default {
    data() {
        return {
            load: false,
            liuye: false,
            liuyAndliuy: false,
            //留言列表
            messageList: [],
            //查询条件
            queryParam: {
                pageNum: 1,
                pageSize: 18,
                type: -1,
                //总共多少页
                pages: 0,
            },
            //留言展示菜单
            liuyMenu: {
                replyList: []
            },
            //留言菜单
            liuyCommitMenu: {
                messageName: '',
                messageColor: "background-color: rgb(252, 175, 162);",
                messageContent: '',
                messageType: 0,
            },
            //留言的留言菜单
            liuyAndliuyCommitMenu: {
                messageId: 0,
                replyName: '',
                replyAvatar: '',
                replyContent: '',
            },
            //颜色选择
            colorIndex: 0,
        }
    },
    created() {
        this.getMessageList()
    },
    methods: {
        // 列表滚动事件
        scrollEvent(e) {
            if ((e.srcElement.offsetHeight + e.srcElement.scrollTop) - e.srcElement.scrollHeight === 0) {
                this.queryParam.pageNum = this.queryParam.pageNum + 1;
                if (this.queryParam.pageNum > this.queryParam.pages) {
                    return;
                }
                this.getMessageList();
            }
        },
        async getMessageList() {
            this.load = true;
            const result = await reqGetMessageList(this.queryParam)
            const ReplyTypeList = [{ replyId: 0, value: '留言' }, { replyId: 1, value: '目标' }, { replyId: 2, value: '理想' }, { replyId: 3, value: '过去' }, { replyId: 4, value: '将来' }, { replyId: 5, value: '爱情' }, { replyId: 6, value: '亲情' }, { replyId: 7, value: '友情' }, { replyId: 8, value: '秘密' }, { replyId: 9, value: '信条' }, { replyId: 10, value: '无题' }]
            if (result.code === 20000) {
                this.queryParam.pages = result.data.pages;
                const MsgList = result.data.records.filter(msgItem => {
                    for (let index = 0; index < ReplyTypeList.length; index++) {
                        const element = ReplyTypeList[index];
                        if (msgItem.messageId === element.replyId) {
                            msgItem.type = element.value
                        }
                    }
                    return msgItem;
                })
                for(var item of MsgList){  
                    this.messageList.push(item);                 
                }
                this.load = false;
            }
        },
        openliuyAndliuy(message) {
            this.liuye = false;
            this.liuyAndliuy = true;
            this.liuyMenu = message;
            this.liuyAndliuyCommitMenu.messageId = message.messageId;

        },
        closeliuyAndliuy() {
            this.liuyAndliuy = false;
        },
        openliuye() {
            this.liuyAndliuy = false;
            this.liuyCommitMenu = {
                messageName: '',
                messageColor: "background-color: rgb(252, 175, 162);",
                messageContent: '',
                messageType: 0,
            }
            this.liuye = true;
        },
        closeliuye() {
            this.liuye = false;
        },
        async addMessage() {
            if (this.liuyCommitMenu.messageName.replace(/\s+/g, "") === "") {
                this.$message({
                    message: "请输入姓名!",
                    type: 'error'
                });
                return;
            }
            if (this.liuyCommitMenu.messageContent.replace(/\s+/g, "") === "") {
                this.$message({
                    message: "请输入留言!",
                    type: 'error'
                });
                return;
            }
            const result = await reqGetinsertMessage(this.liuyCommitMenu)
            if (result.code === 20000) {
                this.$message({
                    message: result.data,
                    type: 'success'
                });
            } else {
                this.$message({
                    message: result.data,
                    type: 'error'
                });
            }
            this.liuye = false;
            this.messageList=[];
            this.getMessageList();
        },
        changeColor(color, index) {
            this.liuyCommitMenu.messageColor = color;
            this.colorIndex = index;
        },
        changeLabel(type) {
            this.liuyCommitMenu.messageType = type
        },
        async addReply() {
            if (this.liuyAndliuyCommitMenu.replyName.replace(/\s+/g, "") === "") {
                this.$message({
                    message: "请输入姓名!",
                    type: 'error'
                });
                return;
            }
            if (this.liuyAndliuyCommitMenu.replyContent.replace(/\s+/g, "") === "") {
                this.$message({
                    message: "请输入评论!",
                    type: 'error'
                });
                return;
            }
            const result = await reqGetinsertReply(this.liuyAndliuyCommitMenu)
            if (result.code === 20000) {
                this.$message({
                    message: result.data,
                    type: 'success'
                });
            } else {
                this.$message({
                    message: result.data,
                    type: 'error'
                });
            }
            this.liuyAndliuyCommitMenu = {
                messageId: 0,
                replyName: '',
                replyAvatar: '',
                replyContent: '',
            }
            this.liuyAndliuy = false;
            this.messageList=[];
            this.getMessageList();
        },
        changeParamType(type) {
            this.queryParam.type = type;
            this.messageList=[];
            this.getMessageList();
        },
        async AddLike(Id) {
            const result = await reqGetAddLike(Id);
            if (result.code === 20000) {
                this.messageList=[];
                this.getMessageList()
            }
        },
        async addMessageReport(Id) {
            this.liuyAndliuy = false;
            const result = await reqGetAddMessageReport(Id);
            if (result.code === 20000) {
                this.$message({
                    message: result.data,
                    type: 'success'
                });
            } else {
                this.$message({
                    message: result.msg,
                    type: 'error'
                });
            }
        }
    },
}
</script>

<style lang="less" scoped>
.main {
    min-height: 92vh;
    height: 100%;

    .message {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        position: relative;

        .title {
            text-align: center;
            padding: 46px 0 40px;

            .name {
                font-size: 56px;
                color: #fff;
                opacity: 0.9;
                font-weight: 600;
            }

            .slogan {
                color: #fff;
                opacity: 0.6;
                padding: 8px;
            }
        }

        .label {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            align-items: center;
            padding-bottom: 26px;

            .select {
                color: #fff;
                font-weight: 600;
                border: 1px solid #fff;
            }

            .label-li {
                padding: 0 14px;
                margin: 0 4px;
                min-width: 16px;
                color: #fff;
                opacity: 0.6;
                height: 28px;
                line-height: 28px;
                border-radius: 16px;
                cursor: pointer;
            }
        }

        .note {
            width: 100vw;
            height: 60vh;
            overflow: auto;
            align-self: flex-start;
            justify-content: center;
            display: flex;
            flex-wrap: wrap;
            align-content: flex-start;

            .note-card {
                margin: 6px;
                transition: all .3s;
            }

            .note-li {
                position: relative;
                height: 240px;
                box-sizing: border-box;
                padding: 12px 20px;
                border: 1px solid transparent;
                width: 288px;
                opacity: 0.9;

                .note-top {
                    display: flex;
                    justify-content: space-between;
                    font-size: 12px;
                    margin-bottom: 16px;
                    color: #F5F6F7;
                }

                .note-content {
                    overflow: hidden;
                    font-family: serif;
                    text-align: left;
                    font-size: 14px;
                    height: 140px;
                    cursor: pointer;
                }

                .note-footer {
                    .note-ifm {
                        display: flex;
                        position: absolute;
                        bottom: 14px;
                        left: 20px;
                        color: #F5F6F7;

                        .like {
                            padding-right: 12px;
                            display: flex;
                            align-items: center;
                            cursor: pointer;

                            .iconfont {
                                font-size: 14px;

                                span {
                                    padding-left: 2px;
                                }
                            }
                        }

                        .comment {
                            display: flex;
                            align-items: center;

                            .iconfont {
                                font-size: 14px;
                            }

                            span {
                                padding-left: 3px;
                            }
                        }
                    }

                    .note-name {
                        font-family: serif;
                        font-weight: 700;
                        font-size: 16px;
                        position: absolute;
                        bottom: 16px;
                        right: 20px;
                    }
                }
            }
        }

    }
}

//添加按钮
.add {
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
    right: 1vw;
    bottom: 12vh;
    width: 56px;
    height: 56px;
    border-radius: 28px;
    background-color: black;

    .iconfont {
        width: 24px;
        height: 36px;
        font-size: 24px;
        position: relative;

    }

    .iconfont .line1 {
        background-color: #fff;
        display: inline-block;
        width: 1.7px;
        height: 20px;
        left: 11px;
        top: 9px;
        position: absolute;
        transform: rotateZ(90deg);
        content: "";
    }

    .iconfont .line2 {
        background-color: #fff;
        width: 2.5px;
        height: 20px;
        left: 11px;
        top: 9px;
        position: absolute;
        content: "";
    }
}

//加载动画
.loading {
    width: 50px;
    height: 50px;
    margin-top: 20px;
    margin-bottom: 20px;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-gap: 10px;
    // counter-reset: number;
    transform: rotate(45deg);

    span {
        --c: red;
        background-color: var(--c);
        position: relative;
        transform: rotate(0);
        animation: blinking 1s infinite;
        animation-delay: var(--d);
    }

    span::before {
        counter-increment: number;
        // content: counter(number);
        color: #fff;
        position: absolute;
        width: 100%;
        height: 100%;
    }

    span:nth-child(7) {
        --c: #f15a5a;
        --d: 0s;
    }

    span:nth-child(4),
    span:nth-child(8) {
        --c: #f0c419;
        --d: 0.2s;
    }

    span:nth-child(1),
    span:nth-child(5),
    span:nth-child(9) {
        --c: #4eba6f;
        --d: 0, 4s;
    }

    span:nth-child(2),
    span:nth-child(6) {
        --c: #2d95bf;
        --d: 0.6s;
    }

    span:nth-child(3) {
        --c: #955ba5;
        --d: 0.8s;
    }
}

//留言
.yk-model {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 64px;
    right: 180px;
    height: 100%;
    z-index: 1001;
    box-sizing: border-box;
    transition: all 1s ease 0s;


    .modal-inner {
        overflow-y: auto;
        position: absolute;
        height: 100%;
        width: 360px;
        background-color: #fff;
        box-shadow: 0 0 20px 0 rgba(0, 0, 0, .08);
        -webkit-backdrop-filter: blur(20px);
        backdrop-filter: blur(20px);

        .model-topbar {
            height: 56px;
            padding: 0 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;

            .title {
                font-size: 16px;
                font-weight: 600;
                color: #202020;
            }
        }

        .model-slot {
            overflow-y: auto;
            margin: 2px;

            .details {
                padding: 0 18px 20px 18px;

                .fback {
                    display: flex;
                    align-items: center;
                    padding-bottom: 10px;

                    .revoke {
                        color: #3b73f0;
                        padding-right: 30px;
                        cursor: pointer;
                    }

                    .report {
                        color: #ff9e55;
                        cursor: pointer;
                    }
                }

                .note-li {
                    position: relative;
                    height: 240px;
                    box-sizing: border-box;
                    padding: 12px 20px;
                    transition: all .3s;
                    border: 1px solid transparent;

                    .note-top {
                        display: flex;
                        justify-content: space-between;
                        font-size: 12px;
                        color: #949494;
                        padding-bottom: 16px;
                    }

                    .note-content {
                        font-family: serif;
                        font-size: 14px;
                        line-height: 1.6;
                        height: 140px;
                        cursor: pointer;
                    }

                    .note-footer {
                        .note-ifm {
                            display: flex;
                            position: absolute;
                            bottom: 14px;
                            left: 20px;
                            color: #949494;

                            .like {
                                padding-right: 12px;
                                display: flex;
                                align-items: center;
                                cursor: pointer;
                            }

                            .comment {
                                padding-bottom: 0px;
                                display: flex;
                                align-items: center;
                            }
                        }

                        .note-name {
                            font-family: serif;
                            font-weight: 700;
                            font-size: 16px;
                            position: absolute;
                            bottom: 16px;
                            right: 20px;
                        }
                    }

                }

                .form {
                    padding: 12px 0;

                    .comment {
                        resize: none;
                        width: 100%;
                        box-sizing: border-box;
                        padding: 8px;
                        height: 56px;
                        border: 1px solid #949494;
                        background: none;
                        color: #202020;
                        display: flex;
                        position: relative;
                        outline: none;
                    }

                    .form-bt {
                        display: flex;
                        justify-content: space-between;
                        padding-top: 6px;

                        .input {
                            box-sizing: border-box;
                            padding: 8px;
                            height: 36px;
                            border: 1px solid #949494;
                            background: none;
                            color: #202020;
                        }

                        .qm {
                            width: 200px;
                        }

                        .base {
                            height: 36px;
                            border-radius: 18px;
                            min-width: 80px;
                            padding: 0 20px;
                        }

                        .primary {
                            background: #202020;
                            color: #fff;
                        }

                    }
                }

                .title {
                    font-weight: 600;
                    padding: 18px 0 12px;
                }

                .comment {
                    display: flex;
                    padding-bottom: 30px;
                    position: relative;

                    .user {
                        margin-right: 8px;
                        width: 28px;
                        height: 28px;
                        border-radius: 50%;
                    }

                    .left {
                        .names {
                            display: flex;
                            padding-bottom: 4px;
                            align-items: center;

                            .name {
                                font-weight: 600;
                                padding-right: 4px;
                            }

                            .time {
                                font-size: 12px;
                                color: #949494;
                            }
                        }
                    }
                }

                .comment:last-child {
                    padding-bottom: 60px;
                }
            }

            .create {
                padding: 0 18px 108px 18px;

                .colors {
                    display: flex;
                    align-items: center;
                    padding: 10px 0;

                    .color {
                        width: 24px;
                        height: 24px;
                        margin-right: 8px;
                    }

                    .selectcolor {
                        border: 1px solid #3b73f0;
                    }
                }

                .card {
                    width: 100%;
                    height: 240px;
                    padding: 12px;
                    box-sizing: border-box;
                    transition: all .3s;

                    .ly,
                    .qm {
                        width: 100%;
                        box-sizing: border-box;
                        padding: 8px;
                        font-family: serif;
                        background: none;
                        color: #202020;
                    }

                    .ly {
                        resize: none;
                        height: 174px;
                        border: none;
                        font-size: 15px;
                        line-height: 1.5;
                    }

                    .qm {
                        height: 36px;
                        border: 1px solid hsla(0, 0%, 100%, .6);
                        text-align: right;
                        font-size: 16px;
                        font-weight: 600;
                    }

                    input {
                        outline: none;
                    }
                }

                .labels {
                    display: flex;
                    flex-wrap: wrap;

                    .labelslected {
                        font-weight: 600;
                        color: #202020;
                        background: #ebebeb;
                    }

                    p {
                        padding: 0 10px;
                        color: var(--fontColorBase, #5b5b5b);
                        line-height: 24px;
                        margin: 8px 2px;
                        cursor: pointer;
                        transition: all .3s;
                        border-radius: 14px;
                    }
                }

                .title {
                    font-weight: 600;
                    padding: 30px 0 12px;
                    text-align: left;
                }

                .mzsm {
                    font-size: 12px;
                    color: #949494;
                }

                .footbt {
                    height: 88px;
                    display: flex;
                    align-items: center;
                    left: 0;
                    bottom: 48px;
                    box-sizing: border-box;
                    width: 100%;
                    padding: 0 20px;
                    -webkit-backdrop-filter: blur(10px);
                    backdrop-filter: blur(10px);

                    .fq {
                        margin-right: 20px;
                    }

                    .secondary {
                        background: none;
                        border: 1px solid #202020;
                        color: #202020;
                    }

                    .yk-button {
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        cursor: pointer;
                        transition: all .2s;
                        box-sizing: border-box;
                    }

                    .lg {
                        height: 48px;
                        border-radius: 24px;
                        min-width: 100px;
                        padding: 0 24px;
                    }

                    .qd {
                        border: none;
                        width: 200px;
                    }

                    .primary {
                        background: #202020;
                        color: #fff;
                    }
                }
            }
        }
    }
}

.transfromX {
    transform: translateX(360px);
}

.transfrom-X {
    transform: translateX(0px);
}

@keyframes blinking {

    0%,
    100% {
        transform: scale(0);
    }

    40%,
    80% {
        transform: scale(1);
    }
}
</style>