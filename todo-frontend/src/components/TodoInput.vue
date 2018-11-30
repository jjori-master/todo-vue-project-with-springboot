<template>
    <div class="inputBox shadow">
        <!--suppress HtmlFormInputWithoutLabel -->
        <input type="text"
               v-model="newTodoItem.content"
               placeholder="Type what you have to do"
               v-on:keyup.enter.stop="addTodo">
        <span class="addContainer" v-on:click="addTodo">
      <i class="addBtn fa fa-plus" aria-hidden="true"></i>
    </span>

        <modal v-if="showModal" @close="showModal = false">
            <h3 slot="header">경고</h3>
            <span slot="body"></span>
            <span slot="footer" @click="showModal = false">
        할 일을 입력 하세요
        <i class="closeModalBtn fa fa-times" aria-hidden="true"></i>
      </span>
        </modal>
    </div>
</template>

<script>

    import Modal from './common/Modal.vue';

    export default {
        name: 'TodoInput',
        data() {
            return {
                newTodoItem: {
                    id: null,
                    content: '',
                },
                showModal: false,
            };
        },
        methods: {
            addTodo() {
                if (!this.newTodoItem.content) {
                    this.showModal = true;
                    return;
                }

                this.$emit('addTodo', {
                    content: this.newTodoItem.content
                });
                this.clearInput();
            },
            clearInput() {
                this.newTodoItem.content = '';
            },
        },
        components: {
            Modal,
        },
    };
</script>

<style scoped>
    input:focus {
        outline: none;
    }

    .inputBox {
        background: white;
        height: 50px;
        line-height: 50px;
        border-radius: 5px;
    }

    .inputBox input {
        border-style: none;
        font-size: 0.9rem;
    }

    .addContainer {
        float: right;
        background: linear-gradient(to right, #6478FB, #8763FB);
        display: inline-block;
        width: 3rem;
        border-radius: 0 5px 5px 0;
    }

    .addBtn {
        color: white;
        vertical-align: middle;
    }
</style>
