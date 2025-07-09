<template>
	<div class="container">
		<header>
			<h1>Bank SG</h1>
		</header>
		<h2>Welcome to SG Bank</h2>
		<div class="services">
			<button @click="checkAccount">Check your account</button>
			<button @click="transfer">Transfer 100 €</button>
		</div>
	</div>
</template>

<script>
import axios from "axios";

export default {
	name: "HomeView",
	data() {
		return {
			csrfToken: null, //token récupéré
		};
	},
	mounted() {
		this.fetchCsrfToken(); // au montage -> récupère le token CSRF
	},
	methods: {
		async fetchCsrfToken() {
			try {
				const response = await axios.get(
					"http://localhost:8080/bank/csrf-token",
					{
						withCredentials: true,
					}
				);
				this.csrfToken = response.data.token; // <-- lis directement depuis la réponse JSON
				console.log("Token CSRF récupéré :", this.csrfToken);
			} catch (err) {
				console.error("Erreur CSRF", err);
			}
		},
		getCsrfToken() {
			// Récupère le cookie XSRF-TOKEN
			const match = document.cookie.match(/XSRF-TOKEN=([^;]+)/);
			return match ? decodeURIComponent(match[1]) : null;
		},
		async transfer() {
			if (!this.csrfToken) {
				alert("Token CSRF introuvable, veuillez vous reconnecter");
				return;
			}
			try {
				const data = {
					toAccountNumber: "FR1234",
					amount: 100,
				};

				const response = await axios.post(
					"http://localhost:8080/bank/transfer",
					data, // données du formulaire
					{
						headers: {
							"X-XSRF-TOKEN": this.csrfToken,
							//"Content-Type": "application/x-www-form-urlencoded",
							"Content-Type": "application/json",
						},
						withCredentials: true,
					}
				);
				alert("Transfert réussi : " + response.data);
			} catch (error) {
				alert(
					"Erreur lors du transfert : " +
						(error.response?.data || error.message)
				);
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

.services {
	display: flex;
	flex-direction: row;
	justify-content: center;
	gap: 20px;
}

button {
	font-size: 1.5rem;
}
</style>