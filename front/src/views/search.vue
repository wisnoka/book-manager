<template>
  <div>
    <div class="container-fluid">
      <Header></Header>
      <div class="container-fluid" style="width: 70%; margin: 30px auto;">

        <div class="container-fluid">
          <table class="table table-bordered table-hover">
            <thead>
            <tr>
              <td>ID</td>
              <td>书名</td>
              <td>作者</td>
              <td>出版社</td>
              <td>ISBN</td>
            </tr>
            </thead>
            <tbody>
            <tr v-for="book in Books">
              <td>{{ book.id }}</td>
              <td>{{ book.name }}</td>
              <td>{{ book.author }}</td>
              <td>{{ book.press }}</td>
              <td>{{ book.isbn }}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";

export default {
  name: "search",
  components: {
    Header
  },
  data() {
    return {
      Books: {}
    }
  },
  methods: {
    list() {
      const _this = this;

      this.$axios.get(this.$route.path).then(response => {
        console.log(response)
        _this.Books = response.data.data
      });
    }
  },
  created() {
    this.list();
  }
}
</script>

<style scoped>

</style>
