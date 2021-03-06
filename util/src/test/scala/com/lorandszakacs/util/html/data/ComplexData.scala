/**
  * Copyright 2015 Lorand Szakacs
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  */
package com.lorandszakacs.util.html.data

/**
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 16 Mar 2015
  *
  */
object ComplexData {

  object Combination {

    val ThreeTopLevelImageSectionsMiddleOneEmpty =
      """
<!DOCTYPE html>
<html>
<body class="no-isotope">


	<div class="top-bar">
		<h2 class="title">Set of the Day album name</h2>
	</div>

	<div class="meta-data">
		<time class="icon-photography"> Jan 24, 2013 </time>
		<span class="photographer">by <a href="link-tophotographer">name-of-photograper</a></span>
	</div>

	<section class="image-section">
		<div class="album-container" data-album-type="blog"
			data-album-id="977051">
			<ul class="photos-container">



				<li class="photo-container" id="thumb-0" data-index="0"><a
					href="link0">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/c3/bb/c3bb92727687d8e2eb5412ec85547e0a.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f2/83/f28361935c7751bba50840f9e1af353f.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f2/83/f28361935c7751bba50840f9e1af353f.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-44" data-index="44"><a
					href="link44">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/c3/6d/c36d044cd72a0261a6edfc6bbffab8e9.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/37/54/3754836c75e45ee9401885aa7c538949.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/37/54/3754836c75e45ee9401885aa7c538949.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>



			</ul>
		</div>
	</section>

	<section class="image-section">no-photo-container here</section>


	<section class="image-section">
		<li class="photo-container"><a href="BOGUS LINK!!"></a>
	</section>
</body>

</html>
      """

    val TwoTopLevelImageSections =
      """
<!DOCTYPE html>
<html>
<body class="no-isotope">


	<div class="top-bar">
		<h2 class="title">Set of the Day album name</h2>
	</div>

	<div class="meta-data">
		<time class="icon-photography"> Jan 24, 2013 </time>
		<span class="photographer">by <a href="link-tophotographer">name-of-photograper</a></span>
	</div>

	<section class="image-section">
		<div class="album-container" data-album-type="blog"
			data-album-id="977051">
			<ul class="photos-container">



				<li class="photo-container" id="thumb-0" data-index="0"><a
					href="link0">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/c3/bb/c3bb92727687d8e2eb5412ec85547e0a.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f2/83/f28361935c7751bba50840f9e1af353f.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f2/83/f28361935c7751bba50840f9e1af353f.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-1" data-index="1"><a
					href="link1">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/2b/42/2b4219a66531a1d6a7d6d9731316d34e.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/90/63/9063306346cd6abe53332cbcacf3ea54.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/90/63/9063306346cd6abe53332cbcacf3ea54.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-2" data-index="2"><a
					href="link2">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/45/a5/45a5351b38ff9d80d8da0fa3ef276e01.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/34/1f/341f5bd767d32e4fe633da9ab0f05463.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/34/1f/341f5bd767d32e4fe633da9ab0f05463.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-3" data-index="3"><a
					href="link3">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/1f/a9/1fa9010ac0fd403ea673d595a78df75a.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/81/eb/81ebba69af75cf092eeca7333501a694.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/81/eb/81ebba69af75cf092eeca7333501a694.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-4" data-index="4"><a
					href="link4">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/13/e3/13e3cfea99dc313269bd5e44381ead4f.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/40/26/402646346066b713c8c96ff61f569148.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/40/26/402646346066b713c8c96ff61f569148.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-5" data-index="5"><a
					href="link5">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/a7/98/a79898bb538ddf95cda8ccf871b7230b.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f5/4e/f54efbe06e7c9bafe0e45bb334979fc2.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f5/4e/f54efbe06e7c9bafe0e45bb334979fc2.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-6" data-index="6"><a
					href="link6">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/ef/b4/efb44bbf610292eb5dd9532643a41536.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/52/cc/52ccaf6ff31b6b00e0b3512a9f086ad1.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/52/cc/52ccaf6ff31b6b00e0b3512a9f086ad1.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-7" data-index="7"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/8816add11beefd4ad24653b67d0496af.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/32/44/3244e1d092ae474ab1f61f89e19842f3.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/0d/eb/0deb1995db8ee7cbe56dba4526db6f67.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/0d/eb/0deb1995db8ee7cbe56dba4526db6f67.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-8" data-index="8"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/d1dc30ffce53d5ad99315536d78b45db.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/c7/45/c745ab523ad0d36d35b67dab35d86f00.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f8/12/f812b549f7673b293d411c0f7ad4c42e.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f8/12/f812b549f7673b293d411c0f7ad4c42e.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-9" data-index="9"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/9a836ed5a7cc3ebd50173bd495a3c583.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/1d/5f/1d5fa0e736c377ddddea5ee6599e2b76.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/aa/b6/aab6b1853b42292fad561d57657924ca.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/aa/b6/aab6b1853b42292fad561d57657924ca.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-10" data-index="10"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/41c8eba47b48dd24928ddb8133a598a9.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/8e/1c/8e1ca1828a42b3f2408c142691621d7b.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/41/a9/41a994d2421c74c474cb0d3cc213e8c5.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/41/a9/41a994d2421c74c474cb0d3cc213e8c5.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-11" data-index="11"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/2a56c489664c1fb3394cad8b4e836e77.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/c6/97/c697762b7d267307a77728a4b1a8f152.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/35/c9/35c9102198483c990bf18ac9ad3fe9c9.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/35/c9/35c9102198483c990bf18ac9ad3fe9c9.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-12" data-index="12"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/9b1404117c3e3445803dcdd375e05f32.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/42/09/42093035a3e611aad0621ad9dd38e067.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/ef/e5/efe5f9143a7c23f8e959263d3cf15aa4.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/ef/e5/efe5f9143a7c23f8e959263d3cf15aa4.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-13" data-index="13"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/4a2e68076b3bfa4b00071653ab5d7a3c.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/2a/f0/2af02ba10f72bace4d56be58aba6770a.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/59/4a/594ad46c1bc55d468689016e297d52ed.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/59/4a/594ad46c1bc55d468689016e297d52ed.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-14" data-index="14"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/f5a858c0a77d9b24fe93c685171fbc62.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/86/cd/86cd4f6fa9903dc4e4ab25fb80439ad3.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/60/80/6080d1a58f8b13291a5a099888ce9a4a.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/60/80/6080d1a58f8b13291a5a099888ce9a4a.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-15" data-index="15"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/fcf6905d71e23fc7b059ce455ec571df.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/eb/be/ebbe8c762733778b6af92995d93bb2d5.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/42/25/42256e7df65d7e08ed36730f3fb6f0c0.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/42/25/42256e7df65d7e08ed36730f3fb6f0c0.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-16" data-index="16"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/a3aa8e9d8cb20e23e8b49bea4f3446b6.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/56/c5/56c5d4453f091177b0a4913360c2e859.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/70/1e/701ecbaf962824f8c1117b8e773c376d.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/70/1e/701ecbaf962824f8c1117b8e773c376d.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-17" data-index="17"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/b474a377dac5d71e71f30d17f13a1d0d.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/b6/aa/b6aabd27bd38d2ed0935554fae1a9b31.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/d7/60/d7601bec2551737854c0185a8ce224f6.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/d7/60/d7601bec2551737854c0185a8ce224f6.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-18" data-index="18"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/4274ebe587c4e00cdbb61e75aae9de2b.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/93/fe/93fe79c343eacbfdc9e0259656838b0c.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f5/a3/f5a3966426b3a5b8d73063e28d51d7c9.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/f5/a3/f5a3966426b3a5b8d73063e28d51d7c9.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-19" data-index="19"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/3cd0e324c143c1919856ff204b670476.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/f0/3c/f03c42ce3587ba31732e6a172f18146e.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/bf/d3/bfd329c80312b2522c2b1e1540a930bc.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/bf/d3/bfd329c80312b2522c2b1e1540a930bc.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-20" data-index="20"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/ca96ff2db0959eee69a18c8c07892ede.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/bd/30/bd3030a87850c9b559d2403011512633.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/35/c4/35c419085c408c42cda492f40f075b9a.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/35/c4/35c419085c408c42cda492f40f075b9a.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-21" data-index="21"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/16523381bf417c1668f6890d7fd78cae.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/4f/9e/4f9e2858053b896888b99629b727d5fa.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/13/8d/138d8aa724e50524aa8802161ab6d655.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/13/8d/138d8aa724e50524aa8802161ab6d655.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-22" data-index="22"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/adacdd03dccb3a286a8ea43c77fa7b8f.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/fb/06/fb068f7b179cc52d4452df12ea747ad5.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/d5/71/d5719eca58170d3f203c36524d1e6c99.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/d5/71/d5719eca58170d3f203c36524d1e6c99.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-23" data-index="23"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/214145432f833c357ba564589c1d073b.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/d7/2b/d72b39a951f142f9362b82d9f818e5ed.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/a0/d2/a0d28c70f258469e878f64dc0f3c6cb7.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/a0/d2/a0d28c70f258469e878f64dc0f3c6cb7.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-24" data-index="24"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/0eab87ab607eca5f03f7ca0361520fbe.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/48/30/48309005a0ad4ef6d0054f109f7063fa.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/ab/24/ab240307b7a3ffd8fdfa85de4765a15d.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/ab/24/ab240307b7a3ffd8fdfa85de4765a15d.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-25" data-index="25"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/70189faf3ea0d93781cfb0e4039e8875.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/30/a2/30a23f1607e8e655e6c53d46b57368e4.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/e1/a9/e1a9815f9b291fd25d0eecddad7f6db3.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/e1/a9/e1a9815f9b291fd25d0eecddad7f6db3.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-26" data-index="26"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/20147db51b95955a1463b0d754ab5086.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/47/3b/473b14f47bbaf7ad1d3ac1246f827ce2.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/2a/48/2a480e5e19dfea4b5ccca95b48fdd69b.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/2a/48/2a480e5e19dfea4b5ccca95b48fdd69b.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-27" data-index="27"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/5720f18c45b887249267e43c3e70a662.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/3b/35/3b3564adfd11e45d42a2dcc231d9bff7.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/02/71/027176c97e3b7d295475b840a1fb772e.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/02/71/027176c97e3b7d295475b840a1fb772e.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-28" data-index="28"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/02eb4dfb2a73b2e541ea90f2b2467605.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/c9/0c/c90cba54ac896df2ef2c09adf991206f.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/56/c2/56c208680486ddd0bf81553ef2794672.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/56/c2/56c208680486ddd0bf81553ef2794672.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-29" data-index="29"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/088cd846a6de25ae1876d8c77482ce18.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/3a/ad/3aad741b3a027bb2dfc83a3dc6754d82.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/6f/43/6f4332004a0a95c0b7891f133bbce8bb.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/6f/43/6f4332004a0a95c0b7891f133bbce8bb.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-30" data-index="30"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/b3ea12304bef6c225ce3c3882d46844c.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/83/02/8302216ebd49daac29fe0e2f369aee9a.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/40/2e/402edbc22574980492b7e0c174efe27c.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/40/2e/402edbc22574980492b7e0c174efe27c.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-31" data-index="31"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/1f6f6c9fb723d3041c0ad035254229f7.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/ca/8c/ca8c50f679215dd2a44d001a43cea1bf.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/30/1a/301a4f24ba3d856e16a9332566d1e0bd.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/30/1a/301a4f24ba3d856e16a9332566d1e0bd.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-32" data-index="32"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/6188ddccba813dba413f87173e6acd24.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/94/c6/94c6711fa4bc0d41aea0a76fe8f60888.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/c6/e6/c6e64b09e50ae5c7862610c56991c04f.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/c6/e6/c6e64b09e50ae5c7862610c56991c04f.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-33" data-index="33"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/1436e71cb3404ec24273825b882db11c.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/6d/72/6d72f26fbee145d50651b762d6eb1d1c.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/a2/e0/a2e00ce7ad3b1e4ab0fd8f9d2b787168.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/a2/e0/a2e00ce7ad3b1e4ab0fd8f9d2b787168.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-34" data-index="34"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/35f624cfc90e6a4d853512d0a37b7c9f.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/9c/9c/9c9cca644f8687e4f33976a81e4c0d44.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/b5/e7/b5e739e6cdf511dd59d9f1483d86b104.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/b5/e7/b5e739e6cdf511dd59d9f1483d86b104.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-35" data-index="35"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/80cd66f29e0b9cf5885c1603d1adfb99.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/ff/53/ff53e194b3e2d260e92f7e7516ff3490.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/25/ec/25ec7e21e0ccff367c776bce20909541.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/25/ec/25ec7e21e0ccff367c776bce20909541.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-36" data-index="36"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/5f61d5e7e8101deade7dbaebc1cf86a7.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/84/08/840866446496cf789edcce1bfe2057e9.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/b8/73/b8731c58a5055d11cdf853111ded9faf.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/b8/73/b8731c58a5055d11cdf853111ded9faf.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-37" data-index="37"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/e871ac84f584ad4adbf4129215c88362.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/4c/a3/4ca36190ddc239cf74d1fa9340f3ded0.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/4e/a3/4ea366da06a098f454e7ff6ba7140a44.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/4e/a3/4ea366da06a098f454e7ff6ba7140a44.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-38" data-index="38"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/1f85c7bbfe5a4a475d091a15939182fb.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/ab/a1/aba1f1a6194ed6307d71592628234c75.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/6a/b2/6ab235cf967b0759de5a6632a4f55305.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/6a/b2/6ab235cf967b0759de5a6632a4f55305.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-39" data-index="39"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/2e166bb4b95375e0566d423ced6a16c2.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/bc/57/bc5786f09411aa5b9a519192f509ff85.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/70/fe/70fe7b601ece3618377115b698d01e06.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/70/fe/70fe7b601ece3618377115b698d01e06.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-40" data-index="40"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/99c0da0fce8a1f3f24438cebae098aa9.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/aa/21/aa21270bf3c945e86be6314c9e0d5171.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/6f/e8/6fe83a4dc33dfdc5f194ac8eb972fe0f.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/6f/e8/6fe83a4dc33dfdc5f194ac8eb972fe0f.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-41" data-index="41"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/71f362e9899dd7164da1a99d9f70a823.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/3a/f9/3af90b0b23f076976611c3bbdc37814c.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/36/1c/361ce47e3290d05d27b0b58a322fb417.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/36/1c/361ce47e3290d05d27b0b58a322fb417.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-42" data-index="42"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/1286a8e3100dda548e42b031b56abbae.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/f8/a7/f8a77646cf5ba216e3688dd2b46ede23.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/6b/b9/6bb9482fac47e9e4224a1cb4d5554a5e.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/6b/b9/6bb9482fac47e9e4224a1cb4d5554a5e.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-43" data-index="43"><a
					href="https://d1a0n9gptf7ayu.cloudfront.net/photos/c9b235cc7bf02eae0bf895c7e109b908.jpg">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/55/c4/55c48d4a48889ae2764a4f955e7cf36e.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/b3/78/b378b8f92909509a532a1231c03a9164.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/b3/78/b378b8f92909509a532a1231c03a9164.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>

				<li class="photo-container" id="thumb-44" data-index="44"><a
					href="link44">



						<figure data-width="" data-height="" data-ratio="1"
							class="ratio-1-1 res-image no-ratio">
							<noscript data-tablet="" data-mobile=""
								data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/c3/6d/c36d044cd72a0261a6edfc6bbffab8e9.jpg"
								data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/37/54/3754836c75e45ee9401885aa7c538949.jpg">
								<img
									src="https://d1a0n9gptf7ayu.cloudfront.net/cache/37/54/3754836c75e45ee9401885aa7c538949.jpg"
									class="ratio-1-1" alt="" />
							</noscript>
						</figure>




				</a></li>



			</ul>
		</div>
	</section>
	<section class="image-section">
		<li class="photo-container"><a
			href="BOGUS LINK!!"></a>
	</section>
</body>

</html>
      """
  }

}
