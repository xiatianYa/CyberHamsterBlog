<template>
    <div class="container">
        <div class="bubbles">
            <span style="--i:11;"></span>
            <span style="--i:12;"></span>
            <span style="--i:24;"></span>
            <span style="--i:10;"></span>
            <span style="--i:14;"></span>
            <span style="--i:23;"></span>
            <span style="--i:18;"></span>
            <span style="--i:16;"></span>
            <span style="--i:19;"></span>
            <span style="--i:20;"></span>
            <span style="--i:22;"></span>
            <span style="--i:25;"></span>
            <span style="--i:18;"></span>
            <span style="--i:21;"></span>
            <span style="--i:15;"></span>
            <span style="--i:13;"></span>
            <span style="--i:26;"></span>
            <span style="--i:17;"></span>
            <span style="--i:13;"></span>
            <span style="--i:28;"></span>
            <span style="--i:11;"></span>
            <span style="--i:12;"></span>
            <span style="--i:24;"></span>
            <span style="--i:10;"></span>
            <span style="--i:14;"></span>
            <span style="--i:23;"></span>
            <span style="--i:18;"></span>
            <span style="--i:16;"></span>
            <span style="--i:19;"></span>
            <span style="--i:20;"></span>
            <span style="--i:22;"></span>
            <span style="--i:25;"></span>
            <span style="--i:18;"></span>
            <span style="--i:21;"></span>
            <span style="--i:15;"></span>
            <span style="--i:13;"></span>
            <span style="--i:26;"></span>
            <span style="--i:17;"></span>
            <span style="--i:13;"></span>
            <span style="--i:28;"></span>
            <span style="--i:25;"></span>
            <span style="--i:22;"></span>
            <span style="--i:25;"></span>
            <span style="--i:24;"></span>
            <span style="--i:21;"></span>
            <span style="--i:23;"></span>
            <span style="--i:26;"></span>
            <span style="--i:24;"></span>
            <span style="--i:25;"></span>
            <span style="--i:24;"></span>
            <span style="--i:23;"></span>
            <span style="--i:13;"></span>
            <span style="--i:16;"></span>
            <span style="--i:19;"></span>
            <span style="--i:22;"></span>
            <span style="--i:18;"></span>
            <span style="--i:13;"></span>
            <span style="--i:65;"></span>
            <span style="--i:13;"></span>
            <span style="--i:18;"></span>
            <span style="--i:12;"></span>
            <span style="--i:15;"></span>
            <span style="--i:14;"></span>
            <span style="--i:10;"></span>
            <span style="--i:16;"></span>
            <span style="--i:18;"></span>
            <span style="--i:18;"></span>
            <span style="--i:16;"></span>
            <span style="--i:21;"></span>
            <span style="--i:23;"></span>
            <span style="--i:18;"></span>
            <span style="--i:16;"></span>
            <span style="--i:17;"></span>
            <span style="--i:21;"></span>
            <span style="--i:37;"></span>
            <span style="--i:23;"></span>
            <span style="--i:18;"></span>
            <span style="--i:26;"></span>
            <span style="--i:13;"></span>
            <span style="--i:19;"></span>
            <span style="--i:21;"></span>
            <span style="--i:23;"></span>
            <span style="--i:26;"></span>
            <span style="--i:24;"></span>
            <span style="--i:25;"></span>
            <span style="--i:14;"></span>
            <span style="--i:23;"></span>
            <span style="--i:13;"></span>
            <span style="--i:26;"></span>
            <span style="--i:29;"></span>
            <span style="--i:28;"></span>
            <span style="--i:25;"></span>
            <span style="--i:12;"></span>
            <span style="--i:25;"></span>
            <span style="--i:14;"></span>
            <span style="--i:21;"></span>
        </div>
    </div>
</template>

<script>
export default {
}
</script>

<style lang="less">
.container {
    position: fixed;
    z-index: -999;
    width: 100%;
}

.bubbles {
    position: relative;
    display: flex;
}

.bubbles span {
    z-index: -99;
    position: relative;
    width: 8px;
    height: 8px;
    background: #4fc3dc;
    margin: 0 4px;
    border-radius: 50%;
    box-shadow: 0 0 0 10px #4fc3dc44,
        0 0 50px #4fc3dc,
        0 0 100px #4fc3dc;
    animation: animate 15s linear infinite;
    animation-duration: calc(200s / var(--i));
}

.bubbles span:nth-child(even) {
    background: #ff2d75;
    box-shadow: 0 0 0 10px #ff2d7544,
        0 0 50px #ff2d75,
        0 0 100 #ff2d75;
}

@keyframes animate {
    0% {
        transform: translateY(100vh) scale(0);
    }

    100% {
        transform: translateY(-10vh) scale(1);
    }
}
</style>