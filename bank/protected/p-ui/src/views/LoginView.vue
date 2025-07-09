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
		// Récupère la valeur du cookie XSRF-TOKEN
		getCsrfTokenFromCookie() {
			const match = document.cookie.match(/XSRF-TOKEN=([^;]+)/);
			return match ? decodeURIComponent(match[1]) : null;
		},

		// backend envoie le cookie XSRF-TOKEN
		async fetchCsrfToken() {
			try {
				await axios.get("http://localhost:8080/bank/csrf-token", {
					withCredentials: true, // Nécessaire pour envoyer et recevoir des cookies
				});
			} catch (err) {
				console.error("Erreur lors de la récupération du token CSRF", err);
			}
		},

		async login() {
			
			if (!this.username || !this.password) {
				alert("Please fill all fields");
				return;
			}

			// Récupérer le cookie CSRF
			await this.fetchCsrfToken();

			const csrfToken = this.getCsrfTokenFromCookie();

			try {
				await axios.post(
					"http://localhost:8080/bank/login",
					{
						username: this.username,
						password: this.password,
					},
					{
						withCredentials: true,
						headers: {
							"X-XSRF-TOKEN": csrfToken, // Ajouter le token CSRF dans les headers
						},
					}
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
