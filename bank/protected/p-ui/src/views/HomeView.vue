<template>
	<div class="container">
		<header>
			<h1>Bank SG</h1>
		</header>
		<h2>Welcome to SG Bank</h2>
		<div class="services">
			<button>Check your account</button>
			<button>Transfer</button>
		</div>
	</div>
</template>

<script>
export default {
	name: "HomeView",
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
