package com.lntinfotech.design.patterns.creational.factory;

public class Blog extends Website {

	@Override
	void creatWebSite() {
		pages.add(new PostPage());
		pages.add(new AboutPage());
		pages.add(new CommentPage());
		pages.add(new ContactPage());

	}

}
