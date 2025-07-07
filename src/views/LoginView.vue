<template>
	<div class="container">
		<header>
			<h1>Bank SG</h1>
		</header>
		<h2>Login</h2>
		<form @submit.prevent="login">
			<div class="inputs">
				<label for="username">Username</label>
				<input v-model="username" required />
				<label for="password">Password</label>
				<input type="password" v-model="password" required />
			</div>
			<button>Login</button>
		</form>
	</div>
</template>

<script>
import axios from "axios";

export default {
	name: "LoginView",
	data() {
		return {
			username: "",
			password: "",
		};
	},
	methods: {
		async login() {
			if (!this.username || !this.password) {
				alert("Please fill all fields");
				return;
			}

			try {
				await axios.post(
					"http://localhost:8080/bank/login",
					{
						username: this.username,
						password: this.password,
					},
					{ withCredentials: true }
				);

				this.$router.push("/home");
			} catch (err) {
				alert("Input not valid");
			}
		},
	},
};
</script>

<style scoped>
.container {
	display: flex;
	flex-direction: column;
}

header {
	text-align: center;
	background-color: red;
}

h2 {
	text-align: center;
}

form {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	gap: 20px;
}

.inputs {
	display: flex;
	flex-direction: column;
	gap: 10px;
}

input {
	margin: 5px;
}
</style>
