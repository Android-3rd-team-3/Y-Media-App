package com.example.ymediaapp.presentation.my_video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ymediaapp.app.AppContainer
import com.example.ymediaapp.app.MyVideoContainer
import com.example.ymediaapp.app.User
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.FragmentMyVideoBinding

class MyVideoFragment : Fragment() {

    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

    private lateinit var appContainer: AppContainer
    private lateinit var myVideoViewModel: MyVideoViewModel
    private lateinit var user: User

    private val rvAdapter by lazy {
        MyVideoRvAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (requireActivity().application as YMediaApplication).appContainer
        appContainer.myVideoContainer = MyVideoContainer(appContainer.videoRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyVideoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appContainer.myVideoContainer?.let {
            myVideoViewModel = ViewModelProvider(this, it.myVideoViewModelFactory)[MyVideoViewModel::class.java]
            user = it.user
        }

        binding.apply {
            Glide.with(this@MyVideoFragment)
                .load(user.profileImage)
                .circleCrop()
                .into(ivProfile)
            Glide.with(this@MyVideoFragment)
                .load(user.backgroundImage)
                .into(ivBackground)

            tvChannelName.text = user.channelName
            tvChannelDescription.text = user.channelDescription
            rvFavoriteVideos.adapter = rvAdapter
//            tvFavoriteVideo.setOnClickListener {
//                myVideoViewModel.addItem(YoutubeVideoEntity(thumbnail="https://i.ytimg.com/vi/PGLx4V680J8/default.jpg",
//                    name="IVE 아이브 'Accendio' MV",
//                    description="""STARSHIP SQUARE (Domestic) : https://bit.ly/3HtQ7GF
//                STARSHIP SQUARE (Global) : https://bit.ly/3JWPxm0
//                        IVE US EXCLUSIVE STORE : https://shop.ive-starship.com/
//
//            IVE Twitter
//            : https://twitter.com/IVEstarship
//            : https://twitter.com/IVE_twt
//            : https://twitter.com/IVEstarship_JP
//            IVE Instagram : https://instagram.com/IVEstarship
//            IVE Facebook: https://fb.me/IVEstarship
//            IVE Fancafe: https://cafe.daum.net/IVEstarship
//            IVE TikTok: https://www.tiktok.com/@IVE.official
//            IVE Weibo: https://weibo.com/ivestarship, videoId=PGLx4V680J8, channelId=UCYDmx2Sfpnaxg488yBpZIGg, isLike=false), YoutubeVideoEntity(thumbnail=https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg, name=aespa 에스파 'Supernova' MV, description=aespa's "Supernova" is out!
//            Listen and download on your favorite platform: https://aespa.lnk.to/Supernova
//
//            aespa's 1st album "Armageddon" will be released on May 27th, 6PM (KST).
//            💿 https://aespa.lnk.to/Armageddon
//            🔗 https://aespa.com
//
//            aespa Official
//                    https://www.youtube.com/c/aespa
//            https://www.instagram.com/aespa_official
//            https://www.tiktok.com/@aespa_official
//            https://twitter.com/aespa_Official
//            https://www.facebook.com/aespa.official
//            https://weibo.com/aespa
//
//            #aespa #Supernova #Armageddon
//            #에스파 #æspa
//            #aespaSupernova #aespaArmageddon
//            aespa 에스파 'Supernova' MV ℗ SM Entertainment, videoId=phuiiNCxRMg, channelId=UCEf_Bc-KVd7onSeifS3py9g, isLike=false), YoutubeVideoEntity(thumbnail=https://i.ytimg.com/vi/1ZmTv44nVxg/default.jpg, name=British Students join Korean Navy: Boot Camp Day 1, description=Korean men do Military Service, and over the years I've visited friends at their army bases, heard horror stories about how difficult boot camp is, and wondered what it would really be like.The British High Schoolers have now graduated, and are the exact age that most Korean men go to boot camp. For the past few months we've been in contact with the Korean Navy, planning our most ambitious series yet.We're taking the boys back to Korea, and this time they're going to the Korean Navy Boot Camp. We've been given permission to film the entire experience under one condition: we don't get any special treatment, everything is authentic. Little did we know this would be one of the hardest things we've ever done.
//
//            Huge thanks to the ROK Ministry of Defence @ROK_MND  ROK Navy Headquarters @ROKNavy  Naval Education & Training Command for helping us film this series.
//
//            Hope you guys enjoy this week's video! If you wanna check out our limited edition merch: https://bit.ly/NavyD-1
//
//            --
//
//
//            한국 남자라면 모두 거쳐야하는 과정이 있죠. 저도 제 친구들 면회도 가보고, 군대 이야기가 시작되면 시간가는 줄 모르고 서로 경험담을 주고받던 친구들을 보며, 실제로 군대를 가게 된다면 어떤 심정일지 궁금했었는데요.
//            저희 영국 고등학생들이 어느덧 졸업을 하고, 대부분의 한국 남자분들이 입대하는 나이가 되었어요. 그래서 지난 몇달간, 한국 국방부와 연락을 하며, 지금까지 중 가장 야심 찬 시리즈를 준비해왔답니다.
//            그리고 오랜 준비 끝에 드디어 학생들을 데리고 한국으로 돌아갑니다. 해군 신병교육대로 입대하는데요.
//            이때까진 몰랐습니다. 저희가 지금까지 해왔던 것 중 가장 힘든 과정이 저흴 기다리고 있을줄은요...
//
//            이번 촬영에 도움을 주신 대한민국 국방부 @ROK_MND  해군본부@ROKNavy, 해군 기군단 관계자 여러분께 깊은 감사의 말씀 드립니다.
//            2024-05-16 15:59:40.906 14892-14892 MainActivity1           com.example.ymediaapp                D  저희 이번 시리즈 굿즈를 원하신다면 :https://bit.ly/NavyD-1, videoId=1ZmTv44nVxg, channelId=UCg-p3lQIqmhh7gHpyaOmOiQ, isLike=false), YoutubeVideoEntity(thumbnail=https://i.ytimg.com/vi/zlaUge08FJY/default.jpg, name=고현정 브이로그 1, description=팀 고현정 제작진입니다.
//
//
//            이 영상은
//                    2024년 4월 초에 촬영된 것으로
//            고현정 배우 님의 티파니앤코 프렌드십 앰버서더 공식 행사 일정을 담고 있습니다
//
//
//            오해가 없길 바라는 마음에 해당 내용을 전달드립니다.
//
//
//            감사합니다.
//
//
//            🙇🏻‍♀️"""
//                    , videoId=UUID.randomUUID().toString(), channelId="UCC7kOHXUEsZnRkBB8kt-lCw", isLike=false))
//            }
        }

        with(myVideoViewModel) {
            favoriteList.observe(viewLifecycleOwner) {
                rvAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        appContainer.myVideoContainer = null
        super.onDestroy()
    }
}